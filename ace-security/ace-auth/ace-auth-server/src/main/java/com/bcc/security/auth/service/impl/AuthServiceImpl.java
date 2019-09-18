package com.bcc.security.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bcc.security.api.vo.user.UserInfo;
import com.bcc.security.auth.common.util.jwt.JWTInfo;
import com.bcc.security.auth.feign.IUserService;
import com.bcc.security.auth.service.AuthService;
import com.bcc.security.auth.util.user.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(String username, String password) throws Exception {
        UserInfo info = userService.validate(username,password);
        String token = "";
        if (!StringUtils.isEmpty(info.getId())) {
            token = jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        return token;
    }

    @Override
    public Boolean invalid(String token) {
        // TODO: 2017/9/11 注销token
        return null;
    }

    @Override
    public String refresh(String oldToken) {
        // TODO: 2017/9/11 刷新token
        return null;
    }

	@Override
	public JWTInfo getInfoFromToken(String token) throws ExpiredJwtException,SignatureException{
		return jwtTokenUtil.getInfoFromToken(token);
	}

	@Override
	public boolean isValid(String token) {
		return jwtTokenUtil.isTokenValid(token);
	}
}
