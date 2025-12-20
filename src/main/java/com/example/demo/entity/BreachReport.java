package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "breach_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Contract related to the breach
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @OneToOne
    @JoinColumn(name = "penalty_calculation_id")
    private PenaltyCalculation penaltyCalculation;

    private int daysDelayed;

    private BigDecimal penaltyAmount;

    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date generatedAt = new Date();
}
