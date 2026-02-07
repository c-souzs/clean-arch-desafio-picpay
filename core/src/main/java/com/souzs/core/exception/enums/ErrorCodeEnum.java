package com.souzs.core.exception.enums;

public enum ErrorCodeEnum {
    ON0001("Tax Number inválido", "ON-0001"),
    ON0002("Tax Number indiponivel", "ON-0002"),
    ON0003("Email indiponivel", "ON-0003"),
    ON0004("Houve um erro na criação do usuário", "ON-0004"),
    ON0005("Não é possível criar uma carteira sem um usuário vinculado", "ON-0005"),
    ON0006("Não é possível criar uma senha de transação sem um Usuário vinculado", "ON-0006"),

    TR0001("Usuário não tem a função transferência disponível", "TR-0001"),
    TR0002("Saldo indisponível", "TR-0002"),
    TR0003("Houve um erro ao realizar a transferencia", "TR-0003"),
    TR0004("Transferencia não autorizada", "TR-0004"),

    TRP0001("Pin inválido", "TRP-0001"),

    WA0001("Carteira não encontrada", "WA-0001"),

    NO0001("Houve um erro na notificação do usuário", "NO-0001"),

    ATH0001("Houve um erro na autenticação", "ATH-0001"),

    PIN0001("Pin de transação bloqueado", "PIN-0001"),
    PIN0002("Pin incorreto, %d tentativa(s) restante(s)", "PIN-0002");

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
