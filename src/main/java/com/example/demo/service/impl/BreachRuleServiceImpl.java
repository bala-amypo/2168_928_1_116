package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repository;

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRule createRule(BreachRule breachRule) {
        breachRule.setActive(true);
        return repository.save(breachRule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule breachRule) {
        BreachRule existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        existing.setRuleName(breachRule.getRuleName());
        existing.setDescription(breachRule.getDescription());
        existing.setPenaltyAmount(breachRule.getPenaltyAmount());

        return repository.save(existing);
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        rule.setActive(false);
        repository.save(rule);
    }
}
