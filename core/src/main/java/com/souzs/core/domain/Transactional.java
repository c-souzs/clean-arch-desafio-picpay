package com.souzs.core.domain;

import com.souzs.core.domain.enums.TransactionalStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transactional {
    private Long id;
    private Wallet fromWallet;
    private Wallet toWallet;
    private BigDecimal value;
    private TransactionalStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Transactional() {
    }

    public Transactional(Long id, Wallet fromWallet, Wallet toWallet, BigDecimal value, TransactionalStatusEnum status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Transactional(Wallet fromWallet, Wallet toWallet, BigDecimal value) {
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.value = value;
        status = TransactionalStatusEnum.CREATED;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getFromWallet() {
        return fromWallet;
    }

    public void setFromWallet(Wallet fromWallet) {
        this.fromWallet = fromWallet;
    }

    public Wallet getToWallet() {
        return toWallet;
    }

    public void setToWallet(Wallet toWallet) {
        this.toWallet = toWallet;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransactionalStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransactionalStatusEnum status) {
        this.status = status;
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

        Transactional that = (Transactional) o;
        return Objects.equals(id, that.id) && fromWallet.equals(that.fromWallet) && toWallet.equals(that.toWallet) && value.equals(that.value) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + fromWallet.hashCode();
        result = 31 * result + toWallet.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }
}
