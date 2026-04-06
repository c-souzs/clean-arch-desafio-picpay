package com.souzs.exception;

import com.souzs.core.exception.BaseException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

public class NotificationException extends BaseException {
    public NotificationException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }
}
