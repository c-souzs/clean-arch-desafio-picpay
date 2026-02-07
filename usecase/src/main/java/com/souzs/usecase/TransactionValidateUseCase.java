package com.souzs.usecase;

import com.souzs.core.domain.Transactional;

public interface TransactionValidateUseCase {
    Boolean validate(Transactional transactional);
}
