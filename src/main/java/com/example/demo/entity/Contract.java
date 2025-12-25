package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contracts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "contractNumber")
})
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contractNumber;

    private String title;
    private String counterpartyName;

    @Column(nullable = false)
    private LocalDate agreedDeliveryDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal baseContractValue;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.ACTIVE;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* ================= RELATIONSHIPS ================= */

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryRecord> deliveryRecords;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<PenaltyCalculation> penaltyCalculations;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<BreachReport> breachReports;

    /* ================= AUDIT ================= */

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() { return id; }

    public String getContractNumber() { return contractNumber; }
    public void setContractNumber(String contractNumber) { this.contractNumber = contractNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCounterpartyName() { return counterpartyName; }
    public void setCounterpartyName(String counterpartyName) { this.counterpartyName = counterpartyName; }

    public LocalDate getAgreedDeliveryDate() { return agreedDeliveryDate; }
    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) {
        this.agreedDeliveryDate = agreedDeliveryDate;
    }

    public BigDecimal getBaseContractValue() { return baseContractValue; }
    public void setBaseContractValue(BigDecimal baseContractValue) {
        this.baseContractValue = baseContractValue;
    }

    public ContractStatus getStatus() { return status; }
    public void setStatus(ContractStatus status) { this.status = status; }
}
