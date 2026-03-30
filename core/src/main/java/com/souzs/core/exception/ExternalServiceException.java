package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public class ExternalServiceException extends BaseException {
    public ExternalServiceException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }
}
