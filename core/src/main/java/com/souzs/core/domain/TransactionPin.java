package com.souzs.core.domain;

import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionPin {
    private Long id;
    private String pin;
    private Wallet wallet;
    private Integer remainingAttempts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final Integer DEFAULT_ATTEMPT = 3;

    protected TransactionPin() {
    }

    // Reconstruir
    public TransactionPin(Long id, String pin, Integer attempt, Wallet wallet, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pin = pin;
        this.wallet = wallet;
        this.remainingAttempts = attempt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Para Usecase
    public TransactionPin(Wallet wallet, String pin) {
        setWallet(wallet);
        setPin(pin);
        this.remainingAttempts = DEFAULT_ATTEMPT;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    private void setWallet(Wallet wallet) {
        if(wallet == null) {
            throw new DomainException(ErrorCodeEnum.ON0006);
        }

        this.wallet = wallet;
    }

    public boolean tryPin(String inputPin) {
        if (getBlocked()) {
            throw new DomainException(ErrorCodeEnum.PIN0001);
        }

        if (!this.pin.equals(inputPin)) {
            remainingAttempts -= 1;
            return false;
        }

        return true;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Long getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        pinIsValid(pin);

        this.pin = pin;
    }

    private void pinIsValid(String pin) {
        if(pin.length() != 8) {
            throw new DomainException(ErrorCodeEnum.TRP0002);
        }
    }

    public Integer getRemainingAttempts() {
        return remainingAttempts;
    }

    public boolean getBlocked() {
        return remainingAttempts == 0;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TransactionPin that = (TransactionPin) o;
        return id.equals(that.id) && Objects.equals(pin, that.pin) && Objects.equals(wallet, that.wallet) && Objects.equals(remainingAttempts, that.remainingAttempts) && Objects.equals(createdAt, that.createdAt) && updatedAt.equals(that.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Objects.hashCode(pin);
        result = 31 * result + Objects.hashCode(wallet);
        result = 31 * result + Objects.hashCode(remainingAttempts);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + updatedAt.hashCode();
        return result;
    }
}
