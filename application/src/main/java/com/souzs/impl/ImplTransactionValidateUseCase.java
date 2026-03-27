package com.souzs.impl;

import com.souzs.core.domain.Transaction;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.TransactionValidateGateway;
import com.souzs.usecase.TransactionValidateUseCase;

public class ImplTransactionValidateUseCase implements TransactionValidateUseCase {
    private TransactionValidateGateway transactionValidateGateway;

    public ImplTransactionValidateUseCase(TransactionValidateGateway transactionValidateGateway) {
        this.transactionValidateGateway = transactionValidateGateway;
    }

    @Override
    public void validate(Transaction transaction) {
        if(!transactionValidateGateway.validate(transaction)) {
            throw new TransferException(ErrorCodeEnum.TR0004);
        }
    }
}
