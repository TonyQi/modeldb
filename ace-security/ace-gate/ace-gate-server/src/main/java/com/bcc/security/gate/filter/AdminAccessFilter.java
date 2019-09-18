package com.bcc.security.gate.filter;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.util.URLEncoder;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.x509.qualified.TypeOfBiometricData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.bcc.security.api.vo.authority.PermissionInfo;
import com.bcc.security.api.vo.log.LogInfo;
import com.bcc.security.auth.client.config.UserAuthConfig;
import com.bcc.security.auth.client.interceptor.ServiceFeignInterceptor;
import com.bcc.security.auth.client.jwt.UserAuthClient;
import com.bcc.security.auth.common.util.jwt.IJWTInfo;
import com.bcc.security.common.context.BaseContextHandler;
import com.bcc.security.common.msg.auth.TokenErrorResponse;
import com.bcc.security.common.msg.auth.TokenForbiddenResponse;
import com.bcc.security.common.util.ClientUtil;
import com.bcc.security.gate.feign.ILogService;
import com.bcc.security.gate.feign.IUserService;
import com.bcc.security.gate.utils.DBLog;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-23 8:25
 */
@Component
@Slf4j
public class AdminAccessFilter extends ZuulFilter {

    private IUserService userService;
    @Autowired
    private ILogService logService;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Value("${zuul.prefix}")
    private String zuulPrefix;
    @Autowired
    private UserAuthClient userAuthUtil;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private EurekaClient discoveryClient;

    @PostConstruct
    public void init() {
        InstanceInfo prodSvcInfo = discoveryClient.getNextServerFromEureka("ACE-ADMIN", false);
        ServiceFeignInterceptor serviceFeignInterceptor = new ServiceFeignInterceptor();
        serviceFeignInterceptor.setUserAuthConfig(userAuthConfig);
        this.userService = Feign.builder().encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(serviceFeignInterceptor)
                .target(IUserService.class, prodSvcInfo.getHomePageUrl());
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        BaseContextHandler.setToken(null);
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            return null;
        }
        IJWTInfo user = null;
        try {
            user = getJWTUser(request, ctx);
        } catch (Exception e) {
            setFailedRequest(JSON.toJSONString(new TokenErrorResponse(e.getMessage())), 200);
            return null;
        }
        List<PermissionInfo> permissionIfs = userService.getAllPermissionInfo();
        // 判断资源是否启用权限约束
        Stream<PermissionInfo> stream = getPermissionIfs(requestUri, method, permissionIfs);
        List<PermissionInfo> result = stream.collect(Collectors.toList());
        PermissionInfo[] permissions = result.toArray(new PermissionInfo[]{});
        if (permissions.length > 0) {
            checkUserPermission(permissions, ctx, user);
        }
        BaseContextHandler.remove();
        return null;
    }

    /**
     * 获取目标权限资源
     *
     * @param requestUri
     * @param method
     * @param serviceInfo
     * @return
     */
    private Stream<PermissionInfo> getPermissionIfs(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
        return serviceInfo.parallelStream().filter(new Predicate<PermissionInfo>() {
            @Override
            public boolean test(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
                        && method.equals(permissionInfo.getMethod());
            }
        });
    }

    private void setCurrentUserInfoAndLog(RequestContext ctx, IJWTInfo user, PermissionInfo pm) {
        String host = ClientUtil.getClientIp(ctx.getRequest());
        ctx.addZuulRequestHeader("userId", user.getId());
        ctx.addZuulRequestHeader("userName", URLEncoder.DEFAULT.encode(user.getName(),Charset.forName("UTF-8")));
        ctx.addZuulRequestHeader("userHost", ClientUtil.getClientIp(ctx.getRequest()));
        LogInfo logInfo = new LogInfo(pm.getMenu(), pm.getName(), pm.getUri(), new Date(), user.getId(), user.getName(), host);
        DBLog.getInstance().setLogService(logService).offerQueue(logInfo);
    }

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private IJWTInfo getJWTUser(HttpServletRequest request, RequestContext ctx) throws Exception {
        String authToken = request.getHeader(userAuthConfig.getTokenHeader());
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter("token");
        }
        ctx.addZuulRequestHeader(userAuthConfig.getTokenHeader(), authToken);
        BaseContextHandler.setToken(authToken);
        return userAuthUtil.getInfoFromToken(authToken);
    }


    private void checkUserPermission(PermissionInfo[] permissions, RequestContext ctx, IJWTInfo user) {
        List<PermissionInfo> permissionInfos = userService.getPermissionByUsername(user.getUniqueName());
        PermissionInfo current = null;
        for (PermissionInfo info : permissions) {
            boolean anyMatch = permissionInfos.parallelStream().anyMatch(new Predicate<PermissionInfo>() {
                @Override
                public boolean test(PermissionInfo permissionInfo) {
                    return permissionInfo.getCode().equals(info.getCode());
                }
            });
            if (anyMatch) {
                current = info;
                break;
            }
        }
        if (current == null) {
            setFailedRequest(JSON.toJSONString(new TokenForbiddenResponse("Token Forbidden!")), 200);
        } else {
            if (!RequestMethod.GET.toString().equals(current.getMethod())) {
                setCurrentUserInfoAndLog(ctx, user, current);
            }
        }
    }


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

}
