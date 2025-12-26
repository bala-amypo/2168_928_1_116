package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyCalculationServiceImpl {

    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository penaltyCalculationRepository) {
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    public PenaltyCalculation save(PenaltyCalculation calculation) {
        return penaltyCalculationRepository.save(calculation);
    }

    public List<PenaltyCalculation> getByContractId(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}
