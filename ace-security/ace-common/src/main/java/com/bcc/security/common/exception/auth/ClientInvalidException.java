package com.bcc.security.common.exception.auth;


import com.bcc.security.common.constant.CommonConstants;
import com.bcc.security.common.exception.BaseException;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientInvalidException extends BaseException {

	private static final long serialVersionUID = -447114244853099205L;

	public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
