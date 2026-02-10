package com.souzs.impl;

import com.souzs.core.domain.Transaction;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.ConsultAuthorizationExternalGateway;
import com.souzs.usecase.TransactionValidateUseCase;

public class ImplTransactionValidateUseCase implements TransactionValidateUseCase {
    private ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway;

    public ImplTransactionValidateUseCase(ConsultAuthorizationExternalGateway consultAuthorizationExternalGateway) {
        this.consultAuthorizationExternalGateway = consultAuthorizationExternalGateway;
    }

    @Override
    public void validate(Transaction transaction) {
        boolean valid = consultAuthorizationExternalGateway.authorization(transaction);

        if(!valid) {
            throw new TransferException(ErrorCodeEnum.TR0004);
        }
    }
}
