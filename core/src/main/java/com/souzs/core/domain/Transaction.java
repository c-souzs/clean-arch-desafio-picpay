package com.souzs.core.domain;

import com.souzs.core.domain.enums.TransactionalStatusEnum;
import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.TransferException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class Transaction {
    private Long id;
    private Wallet fromWallet;
    private Wallet toWallet;
    private BigDecimal value;
    private TransactionalStatusEnum status;
    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(Long id, Wallet fromWallet, Wallet toWallet, BigDecimal value, TransactionalStatusEnum status, LocalDateTime createdAt) {
        this.id = id;
        setWallets(fromWallet, toWallet);
        setValue(value);
        this.status = status;
        this.createdAt = createdAt;
    }

    public Transaction(Wallet fromWallet, Wallet toWallet, BigDecimal value) {
        setWallets(fromWallet, toWallet);
        setValue(value);
        status = TransactionalStatusEnum.CREATED;
        createdAt = LocalDateTime.now();
    }

    private void setWallets(Wallet fromWallet, Wallet toWallet) {
        if(fromWallet == null || toWallet == null) {
            throw new TransferException(ErrorCodeEnum.TR0003);
        }

        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
    }

    public Long getId() {
        return id;
    }

    public Wallet getFromWallet() {
        return fromWallet;
    }

    public Wallet getToWallet() {
        return toWallet;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException(ErrorCodeEnum.TR0005);
        }

        this.value = value;
    }

    public TransactionalStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransactionalStatusEnum status) {
        var finalStates = EnumSet.of(TransactionalStatusEnum.CANCELED, TransactionalStatusEnum.SUCCESS);

        if (finalStates.contains(this.status)) {
            throw new DomainException(ErrorCodeEnum.TR0006);
        }

        if (this.status == null || (this.status == TransactionalStatusEnum.CREATED && finalStates.contains(status))) {
            this.status = status;
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && fromWallet.equals(that.fromWallet) && toWallet.equals(that.toWallet) && value.equals(that.value) && status == that.status && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + fromWallet.hashCode();
        result = 31 * result + toWallet.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + Objects.hashCode(createdAt);
        return result;
    }
}
