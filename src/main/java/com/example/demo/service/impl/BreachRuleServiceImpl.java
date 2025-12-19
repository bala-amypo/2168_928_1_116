package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer delayDays;
    private Double penaltyPercentage;

    // getters & setters
}
