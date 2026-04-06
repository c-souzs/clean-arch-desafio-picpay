package com.souzs.core.domain;

import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionPin {
    private Long id;
    private String pin;
    private Integer remainingAttempts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final Integer DEFAULT_ATTEMPT = 3;

    protected TransactionPin() {
    }

    // Reconstruir
    public TransactionPin(Long id, String pin, Integer attempt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pin = pin;
        this.remainingAttempts = attempt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Para Usecase
    public TransactionPin(String pin) {
        setPin(pin);
        this.remainingAttempts = DEFAULT_ATTEMPT;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void setPin(String pin) {
        pinIsValid(pin);

        this.pin = pin;
        setUpdatedAt();
    }

    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    private void pinIsValid(String pin) {
        if(pin.length() != 8) {
            throw new DomainException(ErrorCodeEnum.TRP0002);
        }
    }

    public boolean tryPin(String inputPin) {
        if (getBlocked()) {
            throw new DomainException(ErrorCodeEnum.PIN0001);
        }

        if (!this.pin.equals(inputPin)) {
            remainingAttempts -= 1;
            setUpdatedAt();
            return false;
        }

        return true;
    }

    public Long getId() {
        return id;
    }

    public String getPin() {
        return pin;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionPin transactionPin = (TransactionPin) o;
        return id != null && id.equals(transactionPin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
