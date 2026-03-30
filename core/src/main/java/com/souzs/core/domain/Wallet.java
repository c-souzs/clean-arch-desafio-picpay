package com.souzs.core.domain;

import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Wallet {
    private Long id;
    private Long userId;
    private BigDecimal balance;
    private TransactionPin transactionPin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Impede de instanciar vazio, fora o farmework
    protected Wallet() {
    }

    // Reconstruir
    public Wallet(Long id, BigDecimal balance, Long userId, TransactionPin transactionPin, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.transactionPin = transactionPin;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Para Usecase
    public Wallet(Long userId, TransactionPin transactionPin) {
        this.balance = BigDecimal.ZERO;
        setUserId(userId);
        setTransactionPin(transactionPin);
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void receive(BigDecimal value) {
        checkValue(value.compareTo(BigDecimal.ZERO) <= 0, ErrorCodeEnum.TR0007);

        this.balance = balance.add(value);
        setUpdatedAt();
    }

    public void transfer(BigDecimal value) {
        checkValue(value.compareTo(BigDecimal.ZERO) <= 0, ErrorCodeEnum.TR0005);
        checkValue(balance.compareTo(value) < 0, ErrorCodeEnum.TR0002);

        this.balance = balance.subtract(value);
        setUpdatedAt();
    }

    private void checkValue(boolean exp, ErrorCodeEnum errorCodeEnum) {
        if(exp) {
            throw new TransferException(errorCodeEnum);
        }
    }

    public void setTransactionPin(TransactionPin transactionPin) {
        if(transactionPin == null) {
            throw new DomainException(ErrorCodeEnum.TRP0001);
        }

        this.transactionPin = transactionPin;
        setUpdatedAt();
    }

    private void setUserId(Long userId) {
        if (userId == null) {
            throw new DomainException(ErrorCodeEnum.ON0005);
        }

        this.userId = userId;
        setUpdatedAt();
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public TransactionPin getTransactionPin() {
        return transactionPin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return id != null && id.equals(wallet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
