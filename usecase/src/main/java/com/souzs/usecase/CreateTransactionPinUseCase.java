package com.souzs.usecase;

import com.souzs.core.domain.TransactionPin;
import com.souzs.core.domain.Wallet;

public interface CreateTransactionPinUseCase {
    TransactionPin create(Wallet wallet, String pin);
}
