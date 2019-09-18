package com.bcc.security.auth.client.exception;

/**
 * Created by ace on 2017/9/15.
 */
public class JwtTokenExpiredException extends Exception {

	private static final long serialVersionUID = -6042051354835842269L;

	public JwtTokenExpiredException(String s) {
        super(s);
    }
}
