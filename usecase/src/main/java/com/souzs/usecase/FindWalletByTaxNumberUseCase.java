package com.souzs.usecase;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.Wallet;

public interface FindWalletByTaxNumberUseCase {
    Wallet findByTaxNumber(TaxNumber taxNumber);
}
