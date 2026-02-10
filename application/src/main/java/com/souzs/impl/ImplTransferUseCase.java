package com.souzs.impl;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.Transaction;
import com.souzs.core.domain.Wallet;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.ConsultAuthorizationExternalGateway;
import com.souzs.gateway.SaveTransferGateway;
import com.souzs.gateway.UpdateBalanceWalletGateway;
import com.souzs.usecase.CreateTransactionUseCase;
import com.souzs.usecase.FindWalletByTaxNumberUseCase;
import com.souzs.usecase.TransferUseCase;

import java.math.BigDecimal;

public class ImplTransferUseCase implements TransferUseCase {
    private FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase;
    private CreateTransactionUseCase createTransactionUseCase;
    private SaveTransferGateway saveTransferGateway;
    private UpdateBalanceWalletGateway updateBalanceWalletGateway;
    private ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway;

    public ImplTransferUseCase(FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase, CreateTransactionUseCase createTransactionUseCase, SaveTransferGateway saveTransferGateway, UpdateBalanceWalletGateway updateBalanceWalletGateway, ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway) {
        this.findWalletByTaxNumberUseCase = findWalletByTaxNumberUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.saveTransferGateway = saveTransferGateway;
        this.updateBalanceWalletGateway = updateBalanceWalletGateway;
        this.consultAuthorizationExternalGateway = consultAuthorizationExternalGateway;
    }

    @Override
    public void transfer(String from, String to, BigDecimal value) {
        TaxNumber fromTaxNumber = new TaxNumber(from);
        TaxNumber toTaxNumber = new TaxNumber(to);

        Wallet fromWallet = findWalletByTaxNumberUseCase.findByTaxNumber(fromTaxNumber);
        Wallet toWallet = findWalletByTaxNumberUseCase.findByTaxNumber(toTaxNumber);

        Transaction transaction = createTransactionUseCase.create(
                fromWallet,
                toWallet,
                value
        );

        boolean valid = consultAuthorizationExternalGateway.authorization(transaction);

        // Minha regra de negocio precisa saber do resultado,
        // pois afeta a intecao do usuario
        if(!valid) {
            throw new TransferException(ErrorCodeEnum.TR0004);
        }

        toWallet.receive(value);
        fromWallet.transfer(value);

        updateBalanceWalletGateway.update(toWallet);
        updateBalanceWalletGateway.update(fromWallet);

        saveTransferGateway.save(transaction);
    }
}
