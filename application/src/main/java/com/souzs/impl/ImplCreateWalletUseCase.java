package com.souzs.impl;

import com.souzs.core.domain.TransactionPin;
import com.souzs.core.domain.User;
import com.souzs.core.domain.Wallet;
import com.souzs.gateway.SaveWalletGateway;
import com.souzs.usecase.CreateWalletUseCase;

public class ImplCreateWalletUseCase implements CreateWalletUseCase {
    private SaveWalletGateway saveWalletGateway;

    public ImplCreateWalletUseCase(SaveWalletGateway saveWalletGateway) {
        this.saveWalletGateway = saveWalletGateway;
    }

    @Override
    public void create(User user, TransactionPin transactionPin) {
        Wallet wallet = new Wallet(user, transactionPin);

        saveWalletGateway.save(wallet);
    }
}
