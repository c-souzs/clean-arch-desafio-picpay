package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public class AuthenticateException extends BaseException {
    public AuthenticateException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }
}
