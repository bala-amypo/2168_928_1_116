package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contractId;
    private String breachType;
    private Double penaltyAmount;

    public BreachReport() {}

    public Long getId() {
        return id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getBreachType() {
        return breachType;
    }

    public void setBreachType(String breachType) {
        this.breachType = breachType;
    }

    public Double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
}
