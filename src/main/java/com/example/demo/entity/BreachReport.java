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
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private LocalDateTime reportGeneratedAt;

    private Integer daysDelayed;

    private BigDecimal penaltyAmount;

    private String remarks;

    @PrePersist
    public void onCreate() {
        reportGeneratedAt = LocalDateTime.now();
    }
}
