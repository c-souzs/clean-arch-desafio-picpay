package com.souzs.core.domain;

import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.TransactionPinException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

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
            throw new DomainException(ErrorCodeEnum.ON0006.getMessage(), ErrorCodeEnum.ON0006.getCode());
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
            throw new TransactionPinException(ErrorCodeEnum.TR0001.getMessage(), ErrorCodeEnum.TR0001.getCode());
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
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TransactionPin that = (TransactionPin) o;
        return Objects.equals(id, that.id) && user.equals(that.user) && pin.equals(that.pin) && attempt.equals(that.attempt) && blocked.equals(that.blocked) && createdAt.equals(that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + user.hashCode();
        result = 31 * result + pin.hashCode();
        result = 31 * result + attempt.hashCode();
        result = 31 * result + blocked.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }
}
