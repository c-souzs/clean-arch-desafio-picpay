package com.souzs.gateway;

import java.math.BigDecimal;

public interface ConsultBalanceGateway {
    BigDecimal consultBalance(Long idWallet);
}
