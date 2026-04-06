package com.souzs.impl;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.TransactionPin;
import com.souzs.core.domain.User;
import com.souzs.core.domain.enums.UserTypeEnum;
import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.FindUserByTaxNumberGateway;
import com.souzs.gateway.SaveUserGateway;
import com.souzs.usecase.*;

import java.time.LocalDateTime;

public class ImplCreateUserUseCase implements CreateUserUseCase {
    private FindUserByTaxNumberGateway findUserByTaxNumberGateway;
    private EmailAvailableUseCase emailAvailableUseCase;
    private SaveUserGateway saveUserGateway;
    private CreateWalletUseCase createWalletUseCase;

    public ImplCreateUserUseCase(FindUserByTaxNumberGateway findUserByTaxNumberGateway, EmailAvailableUseCase emailAvailableUseCase, SaveUserGateway saveUserGateway, CreateWalletUseCase createWalletUseCase) {
        this.findUserByTaxNumberGateway = findUserByTaxNumberGateway;
        this.emailAvailableUseCase = emailAvailableUseCase;
        this.saveUserGateway = saveUserGateway;
        this.createWalletUseCase = createWalletUseCase;
    }


    @Override
    public void create(String email, String password, String fullName, String taxNumber, String type, String inputPinWallet) {
        User hasTaxNumberAv = findUserByTaxNumberGateway.findUserByTaxNumber(
                taxNumber
        );

        boolean hasEmailAv = emailAvailableUseCase.emailAvailable(
                email
        );

        if(hasTaxNumberAv != null) {
            throw new DomainException(ErrorCodeEnum.ON0002);
        }

        if(!hasEmailAv) {
            throw new DomainException(ErrorCodeEnum.ON0003);
        }

        TaxNumber taxNumberUser = new TaxNumber(taxNumber);
        UserTypeEnum userType = UserTypeEnum.valueOf(type);

        User userSaved = new User(
                email, password, fullName,
                taxNumberUser, userType
        );

        userSaved = saveUserGateway.save(userSaved);

        createWalletUseCase.create(userSaved, inputPinWallet);
    }
}
