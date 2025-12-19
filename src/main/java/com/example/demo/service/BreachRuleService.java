package com.example.demo.service;

import com.example.demo.entity.BreachRule;
import java.util.List;

public interface BreachRuleService {

    BreachRule save(BreachRule rule);

    List<BreachRule> getAll();
}
