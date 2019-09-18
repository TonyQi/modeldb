package com.bcc.security.auth.client.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bcc.security.auth.client.exception.JwtIllegalArgumentException;
import com.bcc.security.auth.client.exception.JwtSignatureException;
import com.bcc.security.auth.client.exception.JwtTokenExpiredException;
import com.bcc.security.auth.client.feign.ServiceAuthFeign;
import com.bcc.security.auth.common.util.jwt.JWTInfo;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
public class UserAuthClient {
	@Autowired
	private ServiceAuthFeign serviceAuthFeign;

	public JWTInfo getInfoFromToken(String token) throws Exception {
		try {
			return serviceAuthFeign.getTokenInfo(token).getBody();
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenExpiredException("User token expired!");
		} catch (SignatureException ex) {
			throw new JwtSignatureException("User token signature error!");
		} catch (IllegalArgumentException ex) {
			throw new JwtIllegalArgumentException("User token is null or empty!");
		}
	}
}
