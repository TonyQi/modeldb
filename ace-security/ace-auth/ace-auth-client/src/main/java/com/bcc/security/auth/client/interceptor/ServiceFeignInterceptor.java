package com.bcc.security.auth.client.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcc.security.auth.client.config.UserAuthConfig;
import com.bcc.security.common.context.BaseContextHandler;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 *
 * @author ace
 * @date 2017/9/15
 */
public class ServiceFeignInterceptor implements RequestInterceptor {

    @Autowired
    private UserAuthConfig userAuthConfig;

    public ServiceFeignInterceptor() {}
    
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(userAuthConfig.getTokenHeader(), BaseContextHandler.getToken());
    }


    public void setUserAuthConfig(UserAuthConfig userAuthConfig) {
        this.userAuthConfig = userAuthConfig;
    }
}