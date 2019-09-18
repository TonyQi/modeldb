package com.bcc.security.common.exception.auth;


import com.bcc.security.common.constant.CommonConstants;
import com.bcc.security.common.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class TokenErrorException extends BaseException {

	private static final long serialVersionUID = -3203849322435777194L;

	public TokenErrorException(String message, int status) {
        super(message, CommonConstants.EX_TOKEN_ERROR_CODE);
    }
}
