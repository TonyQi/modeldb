package com.bcc.security.auth.service;

import com.bcc.security.auth.common.util.jwt.JWTInfo;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public interface AuthService {
    String login(String username, String password) throws Exception;
    String refresh(String oldToken);
    boolean isValid(String token);
    Boolean invalid(String token);
    JWTInfo getInfoFromToken(String token) throws ExpiredJwtException,SignatureException;
}
