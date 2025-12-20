package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository ruleRepo;
    private final PenaltyCalculationRepository penaltyRepo;

    public PenaltyCalculationServiceImpl(
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo,
            BreachRuleRepository ruleRepo,
            PenaltyCalculationRepository penaltyRepo) {
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.ruleRepo = ruleRepo;
        this.penaltyRepo = penaltyRepo;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        DeliveryRecord delivery = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new BadRequestException("No delivery record"));

        BreachRule rule = ruleRepo
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new BadRequestException("No active breach rule"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate().toInstant(),
                delivery.getDeliveryDate().toInstant()
        );

        if (daysDelayed < 0) daysDelayed = 0;

        BigDecimal rawPenalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal cap = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        BigDecimal finalPenalty = rawPenalty.min(cap);

        PenaltyCalculation calc = new PenaltyCalculation();
        calc.setContract(contract);
        calc.setDaysDelayed((int) daysDelayed);
        calc.setCalculatedPenalty(finalPenalty);
        calc.setAppliedRule(rule);

        return penaltyRepo.save(calc);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public java.util.List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyRepo.findByContractId(contractId);
    }
}
