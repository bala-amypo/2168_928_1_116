package com.example.demo.service;

import com.example.demo.entity.BreachRule;
import java.util.List;

public interface BreachRuleService {

    void createRule(BreachRule breachRule);

    void updateRule(Long id, BreachRule breachRule);

    BreachRule getRuleById(Long id);

    List<BreachRule> getAllRules();

    void deactivateRule(Long id);
}
