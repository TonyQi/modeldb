package com.bcc.security.common.exception.auth;


import com.bcc.security.common.constant.CommonConstants;
import com.bcc.security.common.exception.BaseException;

/**
 * Created by ace on 2017/9/12.
 */
public class ClientForbiddenException extends BaseException {

	private static final long serialVersionUID = -8526182076935606148L;

	public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
