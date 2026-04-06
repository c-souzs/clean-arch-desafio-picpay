package com.souzs.usecase;

import java.math.BigDecimal;

public interface TransferUseCase {
    void transfer(String fromTaxNumber, String toTaxNumber, BigDecimal value, String inputPin);
}
