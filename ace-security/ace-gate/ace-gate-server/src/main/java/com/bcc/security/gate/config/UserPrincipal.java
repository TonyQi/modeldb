package com.bcc.security.gate.config;

import com.bcc.gate.ratelimit.config.IUserPrincipal;
import com.bcc.security.auth.client.config.UserAuthConfig;
import com.bcc.security.auth.client.jwt.UserAuthClient;
import com.bcc.security.auth.common.util.jwt.IJWTInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ace on 2017/9/23.
 */

public class UserPrincipal implements IUserPrincipal {

    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private UserAuthClient userAuthUtil;

    @Override
    public String getName(HttpServletRequest request) {
        IJWTInfo infoFromToken = getJwtInfo(request);
        return infoFromToken == null ? null : infoFromToken.getUniqueName();
    }

    private IJWTInfo getJwtInfo(HttpServletRequest request) {
        IJWTInfo infoFromToken = null;
        try {
            String authToken = request.getHeader(userAuthConfig.getTokenHeader());
            if(StringUtils.isEmpty(authToken)) {
                infoFromToken = null;
            } else {
                infoFromToken = userAuthUtil.getInfoFromToken(authToken);
            }
        } catch (Exception e) {
            infoFromToken = null;
        }
        return infoFromToken;
    }

}
