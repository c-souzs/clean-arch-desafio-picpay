package com.souzs.gateway;

import com.souzs.core.domain.TransactionPin;

public interface FindTransactionPinByWalletIdGateway {
    TransactionPin findTransactionPinByUser(Long walletId);
}
