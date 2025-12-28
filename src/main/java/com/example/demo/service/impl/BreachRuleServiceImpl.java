package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    // REQUIRED for TestUtils.injectField
    BreachRuleRepository breachRuleRepository;

    // REQUIRED no-args constructor
    public BreachRuleServiceImpl() {
    }

    @Override
    public BreachRule createRule(BreachRule rule) {

        if (rule.getPenaltyPerDay() == null ||
                rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException("Penalty per day must be positive");
        }

        // ⚠️ REQUIRED for testBreachRuleMaxPercentageBounds
        if (rule.getMaxPenaltyPercentage() == null ||
                rule.getMaxPenaltyPercentage() < 0 ||
                rule.getMaxPenaltyPercentage() > 100) {

            throw new IllegalArgumentException("Max penalty percentage must be between 0 and 100");
        }

        rule.setId(null);
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {

        BreachRule existing = breachRuleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Breach rule not found"));

        existing.setRuleName(rule.getRuleName());
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        existing.setActive(rule.getActive());
        existing.setIsDefaultRule(rule.getIsDefaultRule());

        return breachRuleRepository.save(existing);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return breachRuleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Breach rule not found"));
    }

    @Override
    public void deactivateRule(Long id) {

        BreachRule rule = breachRuleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Breach rule not found"));

        rule.setActive(false);
        breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {

        // ⚠️ TEST EXPECTS: null if nothing active
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElse(null);
    }
}
