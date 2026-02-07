package com.souzs.core.exception;

public class AuthenticateException extends RuntimeException {
    private String code;

    public AuthenticateException(String message, String code) {
        super(message);
        this.code = code;
    }
}
