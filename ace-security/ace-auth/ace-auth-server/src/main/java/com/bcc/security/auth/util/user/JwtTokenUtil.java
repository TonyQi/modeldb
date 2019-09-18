package com.bcc.security.auth.util.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bcc.security.auth.common.util.jwt.IJWTInfo;
import com.bcc.security.auth.common.util.jwt.JWTHelper;
import com.bcc.security.auth.common.util.jwt.JWTInfo;
import com.bcc.security.auth.configuration.KeyConfiguration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * Created by ace on 2017/9/10.
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getUserPriKey(),expire);
    }

    public JWTInfo getInfoFromToken(String token) throws ExpiredJwtException,SignatureException {
        return JWTHelper.getInfoFromToken(token, keyConfiguration.getUserPubKey());
    }

    public boolean isTokenValid(String token) {
    	return JWTHelper.isTokenValid(token, keyConfiguration.getUserPubKey());
    }

}
