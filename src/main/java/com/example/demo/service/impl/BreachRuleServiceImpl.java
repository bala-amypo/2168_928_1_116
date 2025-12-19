package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule breachRule) {
        breachRule.setActive(true);
        return breachRuleRepository.save(breachRule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule breachRule) {
        BreachRule existing = getRuleById(id);
        existing.setRuleName(breachRule.getRuleName());
        existing.setDescription(breachRule.getDescription());
        existing.setPenaltyAmount(breachRule.getPenaltyAmount());
        return breachRuleRepository.save(existing);
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return breachRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach rule not found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = getRuleById(id);
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }
}
