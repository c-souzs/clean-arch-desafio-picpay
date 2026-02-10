package com.souzs.impl;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.Wallet;
import com.souzs.core.exception.NotFoundException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.FindWalletByTaxNumberGateway;
import com.souzs.usecase.FindWalletByTaxNumberUseCase;

public class ImplFindWalletByTaxNumberUseCase implements FindWalletByTaxNumberUseCase {
    private FindWalletByTaxNumberGateway findWalletByTaxNumberGateway;

    public ImplFindWalletByTaxNumberUseCase(FindWalletByTaxNumberGateway findWalletByTaxNumberGateway) {
        this.findWalletByTaxNumberGateway = findWalletByTaxNumberGateway;
    }

    @Override
    public Wallet findByTaxNumber(TaxNumber taxNumber) {
        Wallet wallet = findWalletByTaxNumberGateway.findByTaxNumber(taxNumber.getValue());

        if(wallet == null) {
            throw new NotFoundException(ErrorCodeEnum.WA0001);
        }

        return wallet;
    }
}
