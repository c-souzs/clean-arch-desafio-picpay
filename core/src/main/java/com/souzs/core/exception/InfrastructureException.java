package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public class InfrastructureException extends BaseException {
    public InfrastructureException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }
}
