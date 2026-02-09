package com.souzs.core.domain;

import com.souzs.core.domain.enums.UserTypeEnum;
import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Wallet {
    private Long id;
    private BigDecimal balance;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Wallet() {
    }

    // Reconstruir
    public Wallet(Long id, BigDecimal balance, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.balance = balance;
        setUser(user);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Usecase
    public Wallet(User user) {
        this.balance = BigDecimal.ZERO;
        setUser(user);
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    private void setUser(User user) {
        if (user == null) {
            throw new DomainException(ErrorCodeEnum.ON0005);
        }

        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void receiveValue(BigDecimal value) {
        this.balance = balance.add(value);
    }

    public void transfer(BigDecimal value) {
        if(user.getType().equals(UserTypeEnum.SHOPKEEPER)) {
            throw new TransferException(ErrorCodeEnum.TR0001);
        }

        if(balance.compareTo(value) < 0) {
            throw new TransferException(ErrorCodeEnum.TR0002);
        }

        this.balance = balance.subtract(value);
    }

    public User getUser() {
        return user;
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
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) return false;
        
        Wallet wallet = (Wallet) o;
        
        return Objects.equals(user, wallet.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
