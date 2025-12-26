package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
    private Boolean active = true;
    private Boolean isDefaultRule = false;
}
