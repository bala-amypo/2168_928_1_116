package com.example.demo.service;

import com.example.demo.entity.*;

public interface PenaltyCalculationService {

    PenaltyCalculation calculate(
            Contract contract,
            DeliveryRecord record,
            BreachRule rule
    );
}
