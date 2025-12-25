package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService breachRuleService;

    public BreachRuleController(BreachRuleService breachRuleService) {
        this.breachRuleService = breachRuleService;
    }

    @PostMapping
    public BreachRule createRule(@RequestBody BreachRule rule) {
        return breachRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public BreachRule updateRule(@PathVariable Long id,
                                 @RequestBody BreachRule rule) {
        return breachRuleService.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public BreachRule getRule(@PathVariable Long id) {
        return breachRuleService.getRuleById(id);
    }

    @GetMapping
    public List<BreachRule> getAllRules() {
        return breachRuleService.getAllRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
    }
}
