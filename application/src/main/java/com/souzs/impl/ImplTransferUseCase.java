package com.souzs.impl;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.Transaction;
import com.souzs.core.domain.Wallet;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.ConsultAuthorizationExternalGateway;
import com.souzs.gateway.FindWalletByTaxNumberGateway;
import com.souzs.gateway.SaveTransactionGateway;
import com.souzs.gateway.UpdateWalletGateway;
import com.souzs.usecase.CreateTransactionUseCase;
import com.souzs.usecase.TransferUseCase;
import com.souzs.usecase.UserNotificationUseCase;

import java.math.BigDecimal;

public class ImplTransferUseCase implements TransferUseCase {
    private FindWalletByTaxNumberGateway findWalletByTaxNumberGateway;
    private CreateTransactionUseCase createTransactionUseCase;
    private SaveTransactionGateway saveTransactionGateway;
    private UpdateWalletGateway updateWalletGateway;
    private ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway;
    private UserNotificationUseCase userNotificationUseCase;

    public ImplTransferUseCase(
            FindWalletByTaxNumberGateway findWalletByTaxNumberGateway, CreateTransactionUseCase createTransactionUseCase,
            SaveTransactionGateway saveTransactionGateway, UpdateWalletGateway updateWalletGateway,
            ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway, UserNotificationUseCase userNotificationUseCase) {
        this.findWalletByTaxNumberGateway = findWalletByTaxNumberGateway;
        this.createTransactionUseCase = createTransactionUseCase;
        this.saveTransactionGateway = saveTransactionGateway;
        this.updateWalletGateway = updateWalletGateway;
        this.consultAuthorizationExternalGateway = consultAuthorizationExternalGateway;
        this.userNotificationUseCase = userNotificationUseCase;
    }

    @Override
    public void transfer(String from, String to, BigDecimal value, String inputPin) {
        TaxNumber fromTaxNumber = new TaxNumber(from);
        TaxNumber toTaxNumber = new TaxNumber(to);

        Wallet fromWallet = findWalletByTaxNumberGateway.findByTaxNumber(fromTaxNumber.getValue());
        Wallet toWallet = findWalletByTaxNumberGateway.findByTaxNumber(toTaxNumber.getValue());

        Transaction transaction = createTransactionUseCase.create(
                fromWallet.getId(),
                toWallet.getId(),
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
                // Atualiza a wallet completa com o TransactionPin atualizado
                updateWalletGateway.update(fromWallet);
            }

            throw transferException;
        }

        toWallet.receive(value);

        updateWalletGateway.update(toWallet);
        updateWalletGateway.update(fromWallet);

        saveTransactionGateway.save(transaction);

        userNotificationUseCase.notificate(transaction);
    }
}
