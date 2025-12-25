package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;

import java.util.List;

public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repository;

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return repository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = getRuleById(id);
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        existing.setActive(rule.isActive());
        existing.setDefaultRule(rule.isDefaultRule());
        return repository.save(existing);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = getRuleById(id);
        rule.setActive(false);
        repository.save(rule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return repository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));
    }
}
