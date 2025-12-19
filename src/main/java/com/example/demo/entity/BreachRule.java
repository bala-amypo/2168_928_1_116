package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breach_rules")
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "description")
    private String description;

    @Column(name = "penalty_amount")
    private Double penaltyAmount;

    @Column(name = "active")
    private boolean active = true;

    // ✅ No-args constructor
    public BreachRule() {
    }

    // ✅ Getters & Setters (VERY IMPORTANT)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
