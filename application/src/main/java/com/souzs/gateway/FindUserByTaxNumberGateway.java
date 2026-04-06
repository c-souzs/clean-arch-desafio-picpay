package com.souzs.gateway;

import com.souzs.core.domain.User;

public interface FindUserByTaxNumberGateway {
    User findUserByTaxNumber(String taxNumber);
}
