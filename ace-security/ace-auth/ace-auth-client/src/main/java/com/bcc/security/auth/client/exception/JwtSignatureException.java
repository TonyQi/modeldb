package com.bcc.security.auth.client.exception;

/**
 * Created by ace on 2017/9/15.
 */
public class JwtSignatureException extends Exception {

	private static final long serialVersionUID = 5577406282402027710L;

	public JwtSignatureException(String s) {
        super(s);
    }
}
