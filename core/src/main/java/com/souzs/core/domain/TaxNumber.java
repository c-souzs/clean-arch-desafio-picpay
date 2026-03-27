package com.souzs.core.domain;

import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

public class TaxNumber {
    private String value;

    public TaxNumber(String taxNumber) {
        setValue(taxNumber);
    }

    protected TaxNumber() {
    }

    public void setValue(String value) {
        if (!isValid(value)) throw new DomainException(ErrorCodeEnum.ON0001);
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    private boolean isValid(String taxNumber) {
        String digits = taxNumber.replaceAll("[^0-9]", "");
        if (digits.length() == 11) return isCpfValid(digits);
        if (digits.length() == 14) return isCnpjValid(digits);
        return false;
    }

    private boolean isCpfValid(String cpf) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }

        int firstDigit = 11 - (sum % 11);

        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        if (cpf.charAt(9) - '0' != firstDigit) {
            return false;
        }

        sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }

        int secondDigit = 11 - (sum % 11);

        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        return cpf.charAt(10) - '0' == secondDigit;
    }

    private boolean isCnpjValid(String cnpj) {
        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;

        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weights1[i];
        }

        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        if (cnpj.charAt(12) - '0' != firstDigit) {
            return false;
        }

        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        sum = 0;

        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weights2[i];
        }

        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        return cnpj.charAt(13) - '0' == secondDigit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TaxNumber taxNumber = (TaxNumber) o;
        return value.equals(taxNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

