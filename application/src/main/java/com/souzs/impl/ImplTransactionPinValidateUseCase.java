package com.souzs.impl;

import com.souzs.core.domain.TransactionPin;
import com.souzs.core.exception.TransactionPinException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.FindTransactionPinByWalletIdGateway;
import com.souzs.gateway.SaveTransactionPinGateway;
import com.souzs.usecase.TransactionPinValidateUseCase;

public class ImplTransactionPinValidateUseCase implements TransactionPinValidateUseCase {
    private FindTransactionPinByWalletIdGateway findTransactionPinByWalletIdGateway;
    private SaveTransactionPinGateway saveTransactionPinGateway;

    public ImplTransactionPinValidateUseCase(FindTransactionPinByWalletIdGateway findTransactionPinByWalletIdGateway, SaveTransactionPinGateway saveTransactionPinGateway) {
        this.findTransactionPinByWalletIdGateway = findTransactionPinByWalletIdGateway;
        this.saveTransactionPinGateway = saveTransactionPinGateway;
    }

    @Override
    public void validate(Long walletId, TransactionPin transactionPin) {
        TransactionPin transactionPinUser = findTransactionPinByWalletIdGateway.findTransactionPinByUser(walletId);

        if(transactionPinUser.getBlocked()) {
            throw new TransactionPinException(ErrorCodeEnum.PIN0001);
        }

        if (!transactionPinUser.tryPin(transactionPin.getPin())) {
            saveTransactionPinGateway.save(transactionPinUser);
            throw new TransactionPinException(ErrorCodeEnum.PIN0002, transactionPinUser.getRemainingAttempts());
        }

    }
}
