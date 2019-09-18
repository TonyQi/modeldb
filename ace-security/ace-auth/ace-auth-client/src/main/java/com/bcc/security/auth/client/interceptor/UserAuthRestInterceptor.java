package com.bcc.security.auth.client.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bcc.security.auth.client.annotation.IgnoreUserToken;
import com.bcc.security.auth.client.config.UserAuthConfig;
import com.bcc.security.auth.client.jwt.UserAuthClient;
import com.bcc.security.auth.common.util.jwt.IJWTInfo;
import com.bcc.security.common.context.BaseContextHandler;

/**
 * Created by ace on 2017/9/10.
 */
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserAuthClient userAuthUtil;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }

        String token = request.getHeader(userAuthConfig.getTokenHeader());
        if (token!=null) {
        	IJWTInfo infoFromToken = userAuthUtil.getInfoFromToken(token);
            BaseContextHandler.setUsername(infoFromToken.getUniqueName());
            BaseContextHandler.setName(infoFromToken.getName());
            BaseContextHandler.setUserID(infoFromToken.getId());
		}
        return super.preHandle(request, response, handler);
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
