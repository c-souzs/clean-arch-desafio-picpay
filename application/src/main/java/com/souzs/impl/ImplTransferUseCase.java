package com.souzs.impl;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.Transaction;
import com.souzs.core.domain.Wallet;
import com.souzs.gateway.SaveTransferGateway;
import com.souzs.gateway.UpdateBalanceWalletGateway;
import com.souzs.usecase.CreateTransactionUseCase;
import com.souzs.usecase.FindWalletByTaxNumberUseCase;
import com.souzs.usecase.TransactionValidateUseCase;
import com.souzs.usecase.TransferUseCase;

import java.math.BigDecimal;

public class ImplTransferUseCase implements TransferUseCase {
    private FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase;
    private TransactionValidateUseCase transactionValidateUseCase;
    private CreateTransactionUseCase createTransactionUseCase;
    private SaveTransferGateway saveTransferGateway;
    private UpdateBalanceWalletGateway updateBalanceWalletGateway;

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

        transactionValidateUseCase.validate(transaction);

        toWallet.receive(value);
        fromWallet.transfer(value);

        updateBalanceWalletGateway.update(toWallet);
        updateBalanceWalletGateway.update(fromWallet);

        saveTransferGateway.save(transaction);
    }
}
