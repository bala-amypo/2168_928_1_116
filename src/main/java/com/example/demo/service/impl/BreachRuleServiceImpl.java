// package com.example.demo.service.impl;

// import com.example.demo.entity.BreachRule;
// import com.example.demo.repository.BreachRuleRepository;
// import com.example.demo.service.BreachRuleService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// @RequiredArgsConstructor
// public class BreachRuleServiceImpl implements BreachRuleService {

//     private final BreachRuleRepository breachRuleRepository;

//     @Override
//     public BreachRule getActiveRule() {
//         return breachRuleRepository
//                 .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
//                 .orElseThrow(() -> new RuntimeException("Rule not found"));
//     }

//     @Override
//     public List<BreachRule> getAllRules() {
//         return breachRuleRepository.findAll();
//     }
// }
