package com.souzs.core.domain;

import com.souzs.core.domain.enums.UserTypeEnum;

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
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.taxNumber = taxNumber;
        this.type = type;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Para reconstruir a entdidade
    public User(Long id, String email, String password, String fullName, TaxNumber taxNumber,
                UserTypeEnum type, TransactionPin transactionPin, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.taxNumber = taxNumber;
        this.type = type;
        this.transactionPin = transactionPin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public TaxNumber getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(TaxNumber taxNumber) {
        this.taxNumber = taxNumber;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public TransactionPin getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(TransactionPin transactionPin) {
        this.transactionPin = transactionPin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email) && password.equals(user.password) && fullName.equals(user.fullName) && taxNumber.equals(user.taxNumber) && type == user.type && Objects.equals(transactionPin, user.transactionPin) && createdAt.equals(user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + taxNumber.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + transactionPin.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }
}
