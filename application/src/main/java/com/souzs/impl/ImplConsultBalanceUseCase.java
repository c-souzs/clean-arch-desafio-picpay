package com.souzs.impl;

import com.souzs.gateway.ConsultBalanceGateway;
import com.souzs.usecase.ConsultBalanceUseCase;

import java.math.BigDecimal;

public class ImplConsultBalanceUseCase implements ConsultBalanceUseCase {
    private ConsultBalanceGateway consultBalanceGateway;

    public ImplConsultBalanceUseCase(ConsultBalanceGateway consultBalanceGateway) {
        this.consultBalanceGateway = consultBalanceGateway;
    }

    @Override
    public BigDecimal consult(Long walletId) {
        return consultBalanceGateway.consultBalance(walletId);
    }
}
