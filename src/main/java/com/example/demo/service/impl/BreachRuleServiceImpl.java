package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreachRuleServiceImpl implements BreachRuleService {

    // Must be final for Lombok constructor injection
    private final BreachRuleRepository breachRuleRepository;

    @Override
    public BreachRule createRule(BreachRule rule) {

        // Validate penalty per day (Double, NOT BigDecimal)
        if (rule.getPenaltyPerDay() == null || rule.getPenaltyPerDay() <= 0) {
            throw new BadRequestException("Penalty per day must be greater than zero");
        }

        // Validate max penalty percentage
        if (rule.getMaxPenaltyPercentage() == null ||
                rule.getMaxPenaltyPercentage() <= 0 ||
                rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("Invalid max penalty percentage");
        }

        rule.setActive(true);

        return breachRuleRepository.save(rule);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));

        rule.setActive(false);
        breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No active breach rule found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
