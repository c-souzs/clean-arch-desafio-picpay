package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public abstract class BaseException extends RuntimeException {
    private final String code;

    public BaseException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BaseException(ErrorCodeEnum errorCode, Object... args) {
        super(String.format(errorCode.getMessage(), args));
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
