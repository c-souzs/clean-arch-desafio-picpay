package com.souzs.gateway;

import com.souzs.core.domain.Transaction;

public interface TransactionValidateGateway {
    Boolean validate(Transaction transaction);
}
