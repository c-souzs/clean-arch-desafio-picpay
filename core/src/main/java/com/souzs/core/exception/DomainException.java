package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public class DomainException extends BaseException {
    public DomainException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public DomainException(ErrorCodeEnum errorCode, Object... args) {
        super(errorCode, args);
    }
}
