package com.example.demo.repository.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;

import java.util.*;

public class InMemoryPenaltyCalculationRepository implements PenaltyCalculationRepository {

    private final Map<Long, PenaltyCalculation> store = new HashMap<>();
    private long id = 1;

    @Override
    public PenaltyCalculation save(PenaltyCalculation calc) {
        if (calc.getId() == 0) {
            calc.setId(id++);
        }
        store.put(calc.getId(), calc);
        return calc;
    }

    @Override
    public Optional<PenaltyCalculation> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<PenaltyCalculation> findAll() {
        return new ArrayList<>(store.values());
    }
}
