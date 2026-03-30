package com.souzs.impl;

import com.souzs.core.domain.TransactionPin;
import com.souzs.core.domain.User;
import com.souzs.core.domain.Wallet;
import com.souzs.gateway.SaveTransactionPinGateway;
import com.souzs.usecase.CreateTransactionPinUseCase;

public class ImplCreateTransactionPinUseCase implements CreateTransactionPinUseCase {
    private SaveTransactionPinGateway saveTransactionPinGateway;

    public ImplCreateTransactionPinUseCase(SaveTransactionPinGateway saveTransactionPinGateway) {
        this.saveTransactionPinGateway = saveTransactionPinGateway;
    }

    @Override
    public TransactionPin create(Wallet wallet, String pin) {
        TransactionPin transactionPin = new TransactionPin(wallet, pin);

        return saveTransactionPinGateway.save(transactionPin);
    }
}
