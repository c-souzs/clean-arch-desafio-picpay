package com.souzs.gateway;

import com.souzs.core.domain.Wallet;

public interface SaveWalletGateway {
    Wallet save(Wallet wallet);
}
