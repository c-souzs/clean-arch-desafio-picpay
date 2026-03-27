package com.souzs.impl;

import com.souzs.core.domain.TransactionPin;
import com.souzs.core.exception.TransactionPinException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.FindTransactionPinByUserIdGateway;
import com.souzs.usecase.TransactionPinValidateUseCase;

public class ImplTransactionPinValidateUseCase implements TransactionPinValidateUseCase {
    private FindTransactionPinByUserIdGateway findTransactionPinByUserIdGateway;

    public ImplTransactionPinValidateUseCase(FindTransactionPinByUserIdGateway findTransactionPinByUserIdGateway) {
        this.findTransactionPinByUserIdGateway = findTransactionPinByUserIdGateway;
    }

    @Override
    public void validate(Long idUser, TransactionPin transactionPin) {
        TransactionPin transactionPinUser = findTransactionPinByUserIdGateway.findTransactionPinByUser(idUser);

        if(transactionPinUser.getBlocked()) {
            throw new TransactionPinException(ErrorCodeEnum.PIN0001);
        }

        if (!transactionPinUser.tryPin(transactionPin.getPin())) {
            throw new TransactionPinException(ErrorCodeEnum.PIN0002, transactionPinUser.getRemainingAttempts());
        }

    }
}
