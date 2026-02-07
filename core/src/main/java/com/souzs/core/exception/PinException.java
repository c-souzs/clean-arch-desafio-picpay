package com.souzs.core.exception;

public class PinException extends RuntimeException {
    private String code;

    public PinException(String message, String code) {
        super(message);
        this.code = code;
    }
}
