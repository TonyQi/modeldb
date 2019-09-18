package com.bcc.security.auth.client.exception;

/**
 * Created by ace on 2017/9/15.
 */
public class JwtIllegalArgumentException extends Exception {

	private static final long serialVersionUID = -4119990415321037798L;

	public JwtIllegalArgumentException(String s) {
        super(s);
    }
}
