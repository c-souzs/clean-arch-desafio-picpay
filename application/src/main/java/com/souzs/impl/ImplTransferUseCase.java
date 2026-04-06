package com.souzs.impl;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.Transaction;
import com.souzs.core.domain.Wallet;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.*;
import com.souzs.usecase.CreateTransactionUseCase;
import com.souzs.usecase.FindWalletByTaxNumberUseCase;
import com.souzs.usecase.TransferUseCase;
import com.souzs.usecase.UserNotificationUseCase;

import java.math.BigDecimal;

public class ImplTransferUseCase implements TransferUseCase {
    private FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase;
    private CreateTransactionUseCase createTransactionUseCase;
    private SaveTransferGateway saveTransferGateway;
    private UpdateBalanceWalletGateway updateBalanceWalletGateway;
    private ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway;
    private UserNotificationUseCase userNotificationUseCase;
    private SaveTransactionPinGateway saveTransactionPinGateway;

    public ImplTransferUseCase(
            FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase, CreateTransactionUseCase createTransactionUseCase,
            SaveTransferGateway saveTransferGateway, UpdateBalanceWalletGateway updateBalanceWalletGateway,
            ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway, UserNotificationUseCase userNotificationUseCase,
            SaveTransactionPinGateway saveTransactionGateway) {
        this.findWalletByTaxNumberUseCase = findWalletByTaxNumberUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.saveTransferGateway = saveTransferGateway;
        this.updateBalanceWalletGateway = updateBalanceWalletGateway;
        this.consultAuthorizationExternalGateway = consultAuthorizationExternalGateway;
        this.userNotificationUseCase = userNotificationUseCase;
        this.saveTransactionPinGateway = saveTransactionGateway;
    }

    @Override
    public void transfer(String from, String to, BigDecimal value, String inputPin) {
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

        try {
            fromWallet.transfer(value, inputPin);
        } catch (TransferException transferException) {
            // Persiste o transactionPin com a reducao
            // de tentativas restantes
            if(transferException.getCode().equals(ErrorCodeEnum.TR0008.getCode())) {
                saveTransactionPinGateway.save(fromWallet.getTransactionPin());
            }

            throw transferException;
        }

        toWallet.receive(value);

        updateBalanceWalletGateway.update(toWallet);
        updateBalanceWalletGateway.update(fromWallet);

        saveTransferGateway.save(transaction);

        userNotificationUseCase.notificate(transaction);
    }
}
