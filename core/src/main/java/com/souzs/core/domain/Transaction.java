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
    private Long fromWalletId;
    private Long toWalletId;
    private BigDecimal value;
    private TransactionalStatusEnum status;
    private LocalDateTime createdAt;

    protected Transaction() {
    }

    // Reconstruir
    public Transaction(Long id, Long fromWalletId, Long toWalletId, BigDecimal value, TransactionalStatusEnum status, LocalDateTime createdAt) {
        this.id = id;
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Para Usecase
    public Transaction(Long fromWalletId, Long toWalletId, BigDecimal value) {
        setWalletsIds(fromWalletId, toWalletId);
        setValue(value);
        status = TransactionalStatusEnum.CREATED;
        createdAt = LocalDateTime.now();
    }

    private void setWalletsIds(Long fromWalletId, Long toWalletId) {
        if(fromWalletId == null || toWalletId == null) {
            throw new TransferException(ErrorCodeEnum.TR0003);
        }

        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
    }

    public void setValue(BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException(ErrorCodeEnum.TR0005);
        }

        this.value = value;
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

    public Long getId() {
        return id;
    }

    public Long getFromWalletId() {
        return fromWalletId;
    }

    public Long getToWalletId() {
        return toWalletId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TransactionalStatusEnum getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return id != null && id.equals(transaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
