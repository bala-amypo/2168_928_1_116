package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "penalty_calculation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private int daysDelayed;

    @NotNull(message = "Calculated penalty is required")
    private BigDecimal calculatedPenalty;

    @NotNull(message = "Breach rule is required")
    @ManyToOne
    @JoinColumn(name = "rule_id")
    private BreachRule appliedRule;

    @Temporal(TemporalType.TIMESTAMP)
    private Date calculatedAt = new Date();
}
