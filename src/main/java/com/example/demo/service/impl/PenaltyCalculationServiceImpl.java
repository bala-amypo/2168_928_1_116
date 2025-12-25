package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculationServiceImpl(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            BreachRuleRepository breachRuleRepository,
            PenaltyCalculationRepository penaltyCalculationRepository) {

        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        DeliveryRecord delivery = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                delivery.getDeliveryDate());

        if (daysDelayed < 0) daysDelayed = 0;

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxCap = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxCap) > 0) {
            penalty = maxCap;
        }

        PenaltyCalculation calc = new PenaltyCalculation();
        calc.setContract(contract);
        calc.setDaysDelayed((int) daysDelayed);
        calc.setCalculatedPenalty(penalty);
        calc.setAppliedRule(rule);

        return penaltyCalculationRepository.save(calc);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}
