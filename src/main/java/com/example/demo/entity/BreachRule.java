package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;

    // MUST be Double
    private Double penaltyPerDay;

    private Double maxPenaltyPercentage;

    private boolean active;

    private boolean isDefaultRule;
}
