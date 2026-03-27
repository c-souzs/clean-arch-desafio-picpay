package com.souzs.core.domain;

import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionPin {
    private Long id;
    private User user;
    private String pin;
    private Integer remainingAttempts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final Integer DEFAULT_ATTEMPT = 3;

    protected TransactionPin() {
    }

    // Reconstruir
    public TransactionPin(Long id, User user, String pin, Integer attempt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.pin = pin;
        this.remainingAttempts = attempt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Para Usecase
    public TransactionPin(User user, String pin) {
        setUser(user);
        setPin(pin);
        this.remainingAttempts = DEFAULT_ATTEMPT;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    private void setUser(User user) {
        if (user == null) {
            throw new DomainException(ErrorCodeEnum.ON0006);
        }

        this.user = user;
    }

    public Boolean tryPin(String inputPin) {
        if (getBlocked()) {
            throw new DomainException(ErrorCodeEnum.PIN0001);
        }

        if (!this.pin.equals(inputPin)) {
            remainingAttempts -= 1;
            return false;
        }

        return true;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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
            throw new DomainException(ErrorCodeEnum.TRP0001);
        }
    }

    public Integer getRemainingAttempts() {
        return remainingAttempts;
    }

    public Boolean getBlocked() {
        return remainingAttempts == 0;
    }

    public void incorrectAttempt() {
        remainingAttempts-=1;
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
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) return false;
        
        TransactionPin that = (TransactionPin) o;
        
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
