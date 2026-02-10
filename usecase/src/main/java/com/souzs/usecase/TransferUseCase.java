package com.souzs.usecase;

import com.souzs.core.domain.Transaction;

import java.math.BigDecimal;

public interface TransferUseCase {
    void transfer(String fromTaxNumber, String toTaxNumber, BigDecimal value);
;}
