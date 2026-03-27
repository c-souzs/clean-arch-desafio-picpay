package com.souzs.gateway;

import com.souzs.core.domain.TransactionPin;

public interface FindTransactionPinByUserIdGateway {
    TransactionPin findTransactionPinByUser(Long userId);
}
