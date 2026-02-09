package com.souzs.core.domain;

import com.souzs.core.exception.DomainException;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionPin {
    private Long id;
    private User user;
    private String pin;
    private Integer attempt;
    private Boolean blocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final Integer DEFAULT_ATTEMPT = 3;

    protected TransactionPin() {
    }

    // Reconstruir
    public TransactionPin(Long id, User user, String pin, Integer attempt, Boolean blocked, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        setUser(user);
        setPin(pin);
        this.attempt = attempt;
        this.blocked = blocked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Usecase
    public TransactionPin(User user, String pin) {
        setUser(user);
        setPin(pin);
        this.attempt = DEFAULT_ATTEMPT;
        this.blocked = false;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    private void setUser(User user) {
        if (user == null) {
            throw new DomainException(ErrorCodeEnum.ON0006);
        }

        this.user = user;
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

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
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
