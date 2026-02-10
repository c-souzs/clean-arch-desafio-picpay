package com.souzs.gateway;

import com.souzs.core.domain.Wallet;

public interface FindWalletByTaxNumberGateway {
    Wallet findByTaxNumber(String value);
}
