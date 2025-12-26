package com.example.demo.repository;

import com.example.demo.entity.PenaltyCalculation;
import java.util.*;

public interface PenaltyCalculationRepository {
    PenaltyCalculation save(PenaltyCalculation calc);
    Optional<PenaltyCalculation> findById(long id);
    List<PenaltyCalculation> findAll();
}
