package com.souzs.gateway;

import com.souzs.core.domain.Transaction;

public interface ConsultAuthorizationExternalGateway {
    boolean authorization(Transaction transaction);
}
