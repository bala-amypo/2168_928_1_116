package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breach_rules")
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer delayDays;

    private Double penaltyPercentage;

    public BreachRule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public Double getPenaltyPercentage() {
        return penaltyPercentage;
    }

    public void setPenaltyPercentage(Double penaltyPercentage) {
        this.penaltyPercentage = penaltyPercentage;
    }
}
