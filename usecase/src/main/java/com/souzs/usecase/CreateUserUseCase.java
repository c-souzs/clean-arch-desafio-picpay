package com.souzs.usecase;

import java.time.LocalDateTime;

public interface CreateUserUseCase {
    void create(
            String email,
            String password,
            String fullName,
            String taxNumber,
            String type,
            String pin
    );
}
