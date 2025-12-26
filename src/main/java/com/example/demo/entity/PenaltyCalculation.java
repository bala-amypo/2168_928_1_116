package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;

    private BigDecimal calculatedPenalty;

    @ManyToOne
    private BreachRule appliedRule;

    private LocalDateTime calculatedAt;

    @PrePersist
    public void onCreate() {
        calculatedAt = LocalDateTime.now();
    }
}
