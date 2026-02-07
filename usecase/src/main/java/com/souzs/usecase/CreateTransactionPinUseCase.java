package com.souzs.usecase;

import com.souzs.core.domain.User;

public interface CreateTransactionPinUseCase {
    void create(User user, String pin);
}
