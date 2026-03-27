package com.souzs.impl;

import com.souzs.core.exception.AuthenticateException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.UserAuthenticateGateway;
import com.souzs.usecase.UserAuthenticateUseCase;

public class ImplUserAuthenticateUseCase implements UserAuthenticateUseCase {
    private UserAuthenticateGateway userAuthenticateGateway;

    public ImplUserAuthenticateUseCase(UserAuthenticateGateway userAuthenticateGateway) {
        this.userAuthenticateGateway = userAuthenticateGateway;
    }

    @Override
    public void authenticate(String username, String password) {
        if(!userAuthenticateGateway.authenticate(username, password)) {
            throw new AuthenticateException(ErrorCodeEnum.ATH0001);
        }
    }
}
