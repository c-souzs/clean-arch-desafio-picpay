package com.souzs.gateway;

import com.souzs.core.domain.Transaction;

public interface SaveTransactionGateway {
    void save(Transaction transaction);
}
