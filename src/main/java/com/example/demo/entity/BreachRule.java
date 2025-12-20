package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "breach_rule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    private BigDecimal penaltyPerDay;

    private BigDecimal maxPenaltyPercentage;

    private boolean active = true;

    private boolean isDefaultRule;
}
