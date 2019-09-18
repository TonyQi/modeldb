package com.bcc.security.auth.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bcc.security.auth.client.config.UserAuthConfig;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
@ComponentScan({"com.bcc.security.auth.client"})
public class AutoConfiguration {

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }
}
