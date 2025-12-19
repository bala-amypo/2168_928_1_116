package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BreachRuleServiceImpl implements BreachRuleService {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer delayDays;
    private Double penaltyPercentage;

}
