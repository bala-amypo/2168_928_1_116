package com.example.demo.repository;

import com.example.demo.entity.BreachRule;
import java.util.*;

public interface BreachRuleRepository {
    BreachRule save(BreachRule rule);
    Optional<BreachRule> findById(long id);
    List<BreachRule> findAll();
}
