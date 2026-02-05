package com.souzs.core.exception.enums;

public enum ErrorCodeEnum {
    ON0001("Tax Number inválido", "ON-0001"),
    TR0001("Usuário não tem a função transferência disponível", "TR-0001"),
    TR0002("Saldo indisponível", "TR-0002"),
    TRP0001("Pin inválido", "TRP-0001");

    private String message;
    private String code;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
