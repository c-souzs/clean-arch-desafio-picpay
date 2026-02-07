package com.souzs.usecase;

import java.time.LocalDateTime;

public interface CreateUserUseCase {
    void create(
            Long id,
            String email,
            String password,
            String fullName,
            String taxNumber,
            String type,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String pin
    );
}
