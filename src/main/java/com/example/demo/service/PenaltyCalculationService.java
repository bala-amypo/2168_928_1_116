package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord delivery = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record found"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));

        long delayDays = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                delivery.getDeliveryDate()
        );

        int daysDelayed = (int) Math.max(delayDays, 0);

        // Convert Double → BigDecimal SAFELY
        BigDecimal penaltyPerDay = BigDecimal.valueOf(rule.getPenaltyPerDay());

        BigDecimal penalty = penaltyPerDay.multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxPenalty = contract.getBaseContractValue()
                .multiply(
                        BigDecimal.valueOf(rule.getMaxPenaltyPercentage())
                                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                );

        if (penalty.compareTo(maxPenalty) > 0) {
            penalty = maxPenalty;
        }

        PenaltyCalculation calculation = PenaltyCalculation.builder()
                .contract(contract)
                .daysDelayed(daysDelayed)
                .calculatedPenalty(penalty)
                .appliedRule(rule)
                .build();

        return penaltyCalculationRepository.save(calculation);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}
