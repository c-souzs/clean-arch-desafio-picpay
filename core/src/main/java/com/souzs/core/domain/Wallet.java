package com.souzs.core.domain;

import com.souzs.core.domain.enums.UserTypeEnum;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wallet {
    private Long id;
    private BigDecimal balance;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Wallet() {
    }

    public Wallet(Long id, BigDecimal balance, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Wallet(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void receiveValue(BigDecimal value) {
        this.balance = balance.add(value);
    }

    public void transfer(BigDecimal value) {
        if(user.getType().equals(UserTypeEnum.SHOPKEEPER)) {
            throw new TransferException(ErrorCodeEnum.TR0001.getCode(), ErrorCodeEnum.TR0001.getMessage());
        }

        if(balance.compareTo(value) < 0) {
            throw new TransferException(ErrorCodeEnum.TR0002.getMessage(), ErrorCodeEnum.TR0002.getCode());
        }

        this.balance = balance.subtract(value);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
