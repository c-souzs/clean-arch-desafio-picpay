package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public class NotificationException extends BaseException {
    public NotificationException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }
}
