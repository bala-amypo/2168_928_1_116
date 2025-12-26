package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private long daysDelayed;

    // MUST be double
    private double penaltyAmount;

    private String remarks;

    private LocalDateTime reportGeneratedAt;

    @PrePersist
    public void onCreate() {
        this.reportGeneratedAt = LocalDateTime.now();
    }
}
