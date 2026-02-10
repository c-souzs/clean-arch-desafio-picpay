package com.souzs.usecase;

import com.souzs.core.domain.Transaction;

public interface TransactionValidateUseCase {
    void validate(Transaction transaction);
}
