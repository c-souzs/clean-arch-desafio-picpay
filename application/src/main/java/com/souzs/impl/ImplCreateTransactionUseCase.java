package com.souzs.impl;

import com.souzs.core.domain.Transaction;
import com.souzs.core.domain.Wallet;
import com.souzs.gateway.SaveTransactionGateway;
import com.souzs.usecase.CreateTransactionUseCase;

import java.math.BigDecimal;

public class ImplCreateTransactionUseCase implements CreateTransactionUseCase {
    private SaveTransactionGateway saveTransactionGateway;

    public ImplCreateTransactionUseCase(SaveTransactionGateway saveTransactionGateway) {
        this.saveTransactionGateway = saveTransactionGateway;
    }

    @Override
    public Transaction create(Long fromWalletId, Long toWalletId, BigDecimal value) {
        Transaction transaction = new Transaction(
                fromWalletId, toWalletId, value
        );

        saveTransactionGateway.save(transaction);

        return transaction;
    }
}
