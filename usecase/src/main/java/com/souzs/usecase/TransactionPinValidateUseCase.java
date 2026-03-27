package com.souzs.usecase;

import com.souzs.core.domain.TransactionPin;

public interface TransactionPinValidateUseCase {
    void validate(Long idUser, TransactionPin transactionPin);
}
