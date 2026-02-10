package com.souzs.usecase;

import com.souzs.core.domain.Transaction;
import com.souzs.core.domain.Wallet;

import java.math.BigDecimal;

public interface CreateTransactionUseCase {
    Transaction create(Wallet from, Wallet to, BigDecimal value);
}
