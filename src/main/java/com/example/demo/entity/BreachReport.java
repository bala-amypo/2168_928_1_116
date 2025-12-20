package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "breach_report")
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "penalty_calculation_id")
    private PenaltyCalculation penaltyCalculation;

    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date generatedAt = new Date();

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public PenaltyCalculation getPenaltyCalculation() {
        return penaltyCalculation;
    }

    public void setPenaltyCalculation(PenaltyCalculation penaltyCalculation) {
        this.penaltyCalculation = penaltyCalculation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getGeneratedAt() {
        return generatedAt;
    }
}
