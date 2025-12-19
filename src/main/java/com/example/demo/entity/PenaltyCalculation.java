package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contractId;
    private Double delayDays;
    private Double penaltyValue;

    public PenaltyCalculation() {}

    public Long getId() {
        return id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Double getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Double delayDays) {
        this.delayDays = delayDays;
    }

    public Double getPenaltyValue() {
        return penaltyValue;
    }

    public void setPenaltyValue(Double penaltyValue) {
        this.penaltyValue = penaltyValue;
    }
}
