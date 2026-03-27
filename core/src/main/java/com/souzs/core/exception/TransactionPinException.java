package com.souzs.core.exception;

import com.souzs.core.exception.enums.ErrorCodeEnum;

public class TransactionPinException extends BaseException {
    public TransactionPinException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public TransactionPinException(ErrorCodeEnum errorCode, Object... args) {
        super(errorCode, args);
    }
}
