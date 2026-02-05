package com.souzs.core.domain;

import com.souzs.core.exception.TransactionPinException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;

public class TransactionPin {
    private Long id;
    private User user;
    private String pin;
    private Integer attempt;
    private Boolean blocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TransactionPin() {
    }

    public TransactionPin(Long id, User user, String pin, Integer attempt, Boolean blocked, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.pin = pin;
        this.attempt = attempt;
        this.blocked = blocked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TransactionPin(User user, String pin, Integer attempt, Boolean blocked) {
        this.user = user;
        this.pin = pin;
        this.attempt = attempt;
        this.blocked = blocked;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
            throw new TransactionPinException(ErrorCodeEnum.TR0001.getMessage(), ErrorCodeEnum.TR0001.getMessage());
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


}
