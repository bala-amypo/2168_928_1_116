package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository ruleRepo;
    private final PenaltyCalculationRepository calcRepo;

    public PenaltyCalculationServiceImpl(
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo,
            BreachRuleRepository ruleRepo,
            PenaltyCalculationRepository calcRepo) {
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.ruleRepo = ruleRepo;
        this.calcRepo = calcRepo;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord delivery = deliveryRepo.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record"));

        BreachRule rule = ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(), delivery.getDeliveryDate());

        if (daysDelayed < 0) daysDelayed = 0;

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxPenalty = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        PenaltyCalculation calculation = new PenaltyCalculation();
        calculation.setContract(contract);
        calculation.setDaysDelayed((int) daysDelayed);
        calculation.setCalculatedPenalty(penalty.min(maxPenalty));
        calculation.setAppliedRule(rule);

        return calcRepo.save(calculation);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return calcRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Penalty calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return calcRepo.findByContractId(contractId);
    }
}
