package com.souzs.usecase;

import com.souzs.core.domain.TransactionPin;
import com.souzs.core.domain.User;

public interface CreateWalletUseCase {
    void create(User user, TransactionPin transactionPin);
}
