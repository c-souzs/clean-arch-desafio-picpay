package com.souzs.gateway;

import com.souzs.core.domain.TransactionPin;

public interface SaveTransactionPinGateway {
    TransactionPin save(TransactionPin transactionPin);
}
