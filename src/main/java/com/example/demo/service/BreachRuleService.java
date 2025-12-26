package com.example.demo.service;

import com.example.demo.entity.BreachRule;

import java.util.List;

public interface BreachRuleService {

    BreachRule getActiveRule();

    List<BreachRule> getAllRules();
}
