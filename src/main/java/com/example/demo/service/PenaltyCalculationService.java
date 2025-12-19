package com.example.demo.service;

import com.example.demo.entity.PenaltyCalculation;
import java.util.List;

public interface PenaltyCalculationService {

    PenaltyCalculation save(PenaltyCalculation penalty);
    List<PenaltyCalculation> getAll();
    PenaltyCalculation getById(Long id);
    void delete(Long id);
}
