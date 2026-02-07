package com.souzs.core.exception;

public class EmailException extends RuntimeException {
    private String code;

    public EmailException(String message, String code) {
        super(message);
        this.code = code;
    }
}
