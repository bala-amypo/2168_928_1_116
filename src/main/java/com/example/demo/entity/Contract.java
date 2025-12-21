package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
    name = "contracts",
    uniqueConstraints = @UniqueConstraint(columnNames = "contractNumber")
)
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contract number is required")
    private String contractNumber;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Counterparty name is required")
    private String counterpartyName;

    @NotNull(message = "Agreed delivery date is required")
    @Temporal(TemporalType.DATE)
    private Date agreedDeliveryDate;

    @NotNull(message = "Base contract value is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal baseContractValue;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // ===== LIFECYCLE =====

    @PrePersist
    public void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
        status = "ACTIVE";
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = new Date();
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    // 🔹 REQUIRED because service uses setId()
    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCounterpartyName() {
        return counterpartyName;
    }

    public void setCounterpartyName(String counterpartyName) {
        this.counterpartyName = counterpartyName;
    }

    public Date getAgreedDeliveryDate() {
        return agreedDeliveryDate;
    }

    public void setAgreedDeliveryDate(Date agreedDeliveryDate) {
        this.agreedDeliveryDate = agreedDeliveryDate;
    }

    public BigDecimal getBaseContractValue() {
        return baseContractValue;
    }

    public void setBaseContractValue(BigDecimal baseContractValue) {
        this.baseContractValue = baseContractValue;
    }

    public String getStatus() {
        return status;
    }

    // 🔹 REQUIRED because service uses setStatus()
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
