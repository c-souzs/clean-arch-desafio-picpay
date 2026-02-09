package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public class NotFoundException extends BaseException {
    public NotFoundException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }
}
