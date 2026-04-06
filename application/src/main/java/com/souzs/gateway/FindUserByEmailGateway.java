package com.souzs.gateway;

import com.souzs.core.domain.User;

public interface FindUserByEmailGateway {
    User findUserByTaxNumber(String taxNumber);
}
