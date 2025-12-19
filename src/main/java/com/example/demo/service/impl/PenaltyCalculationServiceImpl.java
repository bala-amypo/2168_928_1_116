package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repository;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository repository) {
        this.repository = repository;
    }

    public PenaltyCalculation save(PenaltyCalculation penalty) {
        return repository.save(penalty);
    }

    public List<PenaltyCalculation> getAll() {
        return repository.findAll();
    }

    public PenaltyCalculation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
