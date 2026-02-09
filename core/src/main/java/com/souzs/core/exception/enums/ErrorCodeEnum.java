package com.souzs.core.exception.enums;

public enum ErrorCodeEnum {
    ON0001("CPF/CNPJ inválido: o documento informado não passou na validação", "ON-0001"),
    ON0002("CPF/CNPJ já cadastrado: este documento já está vinculado a outra conta", "ON-0002"),
    ON0003("E-mail já cadastrado: este endereço já está vinculado a outra conta", "ON-0003"),
    ON0004("Dados obrigatórios ausentes: e-mail, senha, nome, documento e tipo são obrigatórios", "ON-0004"),
    ON0005("Carteira sem usuário: é necessário vincular um usuário para criar a carteira", "ON-0005"),
    ON0006("Pin de transação sem usuário: é necessário vincular um usuário para criar o pin", "ON-0006"),
    ON0007("Senha fraca: a senha deve ter no mínimo 7 caracteres, 1 maiúscula, 1 número e 1 caractere especial", "ON-0007"),

    TR0001("Transferência não permitida: lojistas não podem realizar transferências", "TR-0001"),
    TR0002("Saldo insuficiente: o valor da transferência excede o saldo disponível", "TR-0002"),
    TR0003("Carteiras inválidas: as carteiras de origem e destino são obrigatórias", "TR-0003"),
    TR0004("Transferência não autorizada: a operação foi negada pelo serviço de autorização", "TR-0004"),
    TR0005("Valor inválido: o valor da transação não pode ser negativo", "TR-0005"),
    TR0006("Transação finalizada: não é possível alterar uma transação já concluída ou cancelada", "TR-0006"),

    TRP0001("Pin de transação inválido: o pin deve ter exatamente 8 caracteres", "TRP-0001"),

    WA0001("Carteira não encontrada: nenhuma carteira corresponde ao identificador informado", "WA-0001"),

    NO0001("Falha na notificação: não foi possível enviar a notificação ao usuário", "NO-0001"),

    ATH0001("Falha na autenticação: não foi possível autenticar o usuário", "ATH-0001"),

    PIN0001("Pin bloqueado: o pin de transação foi bloqueado por excesso de tentativas", "PIN-0001"),
    PIN0002("Pin incorreto: %d tentativa(s) restante(s) antes do bloqueio", "PIN-0002");

    private final String message;
    private final String code;

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
