package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "penalty_calculation")
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private int daysDelayed;

    private BigDecimal calculatedPenalty;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private BreachRule appliedRule;

    @Temporal(TemporalType.TIMESTAMP)
    private Date calculatedAt = new Date();

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(int daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public BigDecimal getCalculatedPenalty() {
        return calculatedPenalty;
    }

    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }

    public BreachRule getAppliedRule() {
        return appliedRule;
    }

    public void setAppliedRule(BreachRule appliedRule) {
        this.appliedRule = appliedRule;
    }

    public Date getCalculatedAt() {
        return calculatedAt;
    }
}
