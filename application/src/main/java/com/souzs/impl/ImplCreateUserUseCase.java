package com.souzs.impl;

import com.souzs.core.domain.TaxNumber;
import com.souzs.core.domain.TransactionPin;
import com.souzs.core.domain.User;
import com.souzs.core.domain.enums.UserTypeEnum;
import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;
import com.souzs.gateway.SaveUserGateway;
import com.souzs.usecase.*;

import java.time.LocalDateTime;

public class ImplCreateUserUseCase implements CreateUserUseCase {
    private TaxNumberAvailableUseCase taxNumberAvailableUseCase;
    private EmailAvailableUseCase emailAvailableUseCase;
    private SaveUserGateway saveUserGateway;
    private CreateWalletUseCase createWalletUseCase;
    private CreateTransactionPinUseCase createTransactionPinUseCase;

    public ImplCreateUserUseCase(TaxNumberAvailableUseCase taxNumberAvailableUseCase, EmailAvailableUseCase emailAvailableUseCase, SaveUserGateway saveUserGateway, CreateWalletUseCase createWalletUseCase, CreateTransactionPinUseCase createTransactionPinUseCase) {
        this.taxNumberAvailableUseCase = taxNumberAvailableUseCase;
        this.emailAvailableUseCase = emailAvailableUseCase;
        this.saveUserGateway = saveUserGateway;
        this.createWalletUseCase = createWalletUseCase;
        this.createTransactionPinUseCase = createTransactionPinUseCase;
    }


    @Override
    public void create(String email, String password, String fullName, String taxNumber, String type, String pin) {
        boolean hasTaxNumberAv = taxNumberAvailableUseCase.taxNumberAvailable(
                taxNumber
        );

        boolean hasEmailAv = emailAvailableUseCase.emailAvailable(
                email
        );

        if(!hasTaxNumberAv) {
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

        createTransactionPinUseCase.create(userSaved, pin);

        createWalletUseCase.create(userSaved);
    }
}
