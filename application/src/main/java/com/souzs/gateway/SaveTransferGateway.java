package com.souzs.gateway;

import com.souzs.core.domain.Transaction;

public interface SaveTransferGateway {
    void save(Transaction transaction);
}
