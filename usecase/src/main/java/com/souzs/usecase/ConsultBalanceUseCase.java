package com.souzs.usecase;

import java.math.BigDecimal;

public interface ConsultBalanceUseCase {
    BigDecimal consult(Long walletId);
}
