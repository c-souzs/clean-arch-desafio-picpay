package com.souzs.core.domain;

import com.souzs.core.domain.enums.UserTypeEnum;
import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private TaxNumber taxNumber;
    private UserTypeEnum type;
    private TransactionPin transactionPin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Impede de instanciar vazio, fora o farmework
    protected User() {
    }

    // Usado no usecase
    public User(String email, String password, String fullName, TaxNumber taxNumber, UserTypeEnum type) {
        setValuesNonNull(email, password, fullName, taxNumber, type);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Para reconstruir a entdidade
    public User(Long id, String email, String password, String fullName, TaxNumber taxNumber,
                UserTypeEnum type, TransactionPin transactionPin, LocalDateTime createdAt, LocalDateTime updatedAt) {
        setValuesNonNull(email, password, fullName, taxNumber, type);
        this.transactionPin = transactionPin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void setValuesNonNull(String email, String password, String fullName, TaxNumber taxNumber,
                                  UserTypeEnum type) {
        validFieldsNonNull(email, password, fullName, taxNumber, type);
        setEmail(email);
        setPassword(password);
        this.fullName = fullName;
        this.taxNumber = taxNumber;
        this.type = type;
    }

    private void validFieldsNonNull(String email, String password, String fullName, TaxNumber taxNumber,
                                    UserTypeEnum type) {
        if(
                isInvalidString(email) ||
                isInvalidString(password) ||
                isInvalidString(fullName) ||
                taxNumber == null ||
                type == null
        ) {
            throw new DomainException(ErrorCodeEnum.ON0004);
        }
    }

    private boolean isInvalidString(String value) {
        return value == null || value.isBlank();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        boolean valid = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        if(!valid) {
            throw new DomainException(ErrorCodeEnum.ON0003);
        }

        this.email = email;
        setUpdatedAt();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        boolean valid = password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{7,}$");

        if(!valid) {
            throw new DomainException(ErrorCodeEnum.ON0007);
        }

        this.password = password;
        setUpdatedAt();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        setUpdatedAt();
    }

    public TaxNumber getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(TaxNumber taxNumber) {
        this.taxNumber = taxNumber;
        setUpdatedAt();
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
        setUpdatedAt();
    }

    public TransactionPin getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(TransactionPin transactionPin) {
        this.transactionPin = transactionPin;
        setUpdatedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id) && email.equals(user.email) && password.equals(user.password) && fullName.equals(user.fullName) && Objects.equals(taxNumber, user.taxNumber) && type == user.type && Objects.equals(transactionPin, user.transactionPin) && createdAt.equals(user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + Objects.hashCode(taxNumber);
        result = 31 * result + Objects.hashCode(type);
        result = 31 * result + Objects.hashCode(transactionPin);
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }
}
