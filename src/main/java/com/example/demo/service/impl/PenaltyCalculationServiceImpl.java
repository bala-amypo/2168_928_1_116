package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repository;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository repository) {
        this.repository = repository;
    }

    @Override
    public PenaltyCalculation calculate(Contract contract,
                                        DeliveryRecord record,
                                        BreachRule rule) {

        long days = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate()
        );

        if (days < 0) days = 0;

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(days));

        BigDecimal maxPenalty = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxPenalty) > 0) {
            penalty = maxPenalty;
        }

        PenaltyCalculation calc = PenaltyCalculation.builder()
                .contract(contract)
                .deliveryRecord(record)
                .breachRule(rule)
                .daysDelayed((int) days)
                .calculatedPenalty(penalty)
                .build();

        return repository.save(calc);
    }
}
