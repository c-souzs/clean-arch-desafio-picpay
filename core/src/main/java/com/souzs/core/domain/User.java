package com.souzs.core.domain;

import com.souzs.core.domain.enums.UserTypeEnum;
import com.souzs.core.exception.DomainException;
import com.souzs.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private TaxNumber taxNumber;
    private UserTypeEnum type;
    private Long walletId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Impede de instanciar vazio, fora o farmework
    protected User() {
    }

    // Para Usecase
    public User(String email, String password, String fullName, TaxNumber taxNumber, UserTypeEnum type) {
        setValuesNonNull(email, password, fullName, taxNumber, type);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Reconstruir
    public User(Long id, String email, String password, String fullName, TaxNumber taxNumber,
                UserTypeEnum type, Long walletId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.taxNumber = taxNumber;
        this.type = type;
        this.walletId = walletId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void setValuesNonNull(String email, String password, String fullName, TaxNumber taxNumber,
                                  UserTypeEnum type) {
        validFieldsNonNull(email, password, fullName, taxNumber, type);
        setEmail(email);
        setPassword(password);
        this.fullName = fullName;
        this.taxNumber = taxNumber;
        this.type = type;
    }

    private void validFieldsNonNull(String email, String password, String fullName, TaxNumber taxNumber,
                                    UserTypeEnum type) {
        checkValueNull(email);
        checkValueNull(password);
        checkValueNull(fullName);
        checkValueNull(taxNumber);
        checkValueNull(type);
    }

    private void checkValueNull(Object value) {
        if(value == null || (value instanceof String && ((String) value).isBlank())) {
            throw new DomainException(ErrorCodeEnum.ON0004);
        }
    }

    public void setEmail(String email) {
        boolean valid = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        if(!valid) {
            throw new DomainException(ErrorCodeEnum.ON0008);
        }

        this.email = email;
        setUpdatedAt();
    }

    public void setPassword(String password) {
        boolean valid = password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{7,}$");

        if(!valid) {
            throw new DomainException(ErrorCodeEnum.ON0007);
        }

        this.password = password;
        setUpdatedAt();
    }

    public void setFullName(String fullName) {
        checkValueNull(fullName);

        this.fullName = fullName;
        setUpdatedAt();
    }

    public void setTaxNumber(TaxNumber taxNumber) {
        checkValueNull(taxNumber);

        this.taxNumber = taxNumber;
        setUpdatedAt();
    }

    public void setType(UserTypeEnum type) {
        checkValueNull(type);

        this.type = type;
        setUpdatedAt();
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public TaxNumber getTaxNumber() {
        return taxNumber;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public Long getWalletId() {
        return walletId;
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
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
