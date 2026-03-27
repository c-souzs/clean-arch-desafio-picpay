package com.souzs.impl;

import com.souzs.core.domain.User;
import com.souzs.gateway.ConsultBalanceGateway;
import com.souzs.usecase.ConsultBalanceUseCase;

import java.math.BigDecimal;

public class ImplConsultBalanceUseCase implements ConsultBalanceUseCase {
    private ConsultBalanceGateway consultBalanceGateway;

    @Override
    public BigDecimal consult(User user) {
        Long idWallet = user.getWallet().getId();
        return consultBalanceGateway.consultBalance(idWallet);
    }
}
