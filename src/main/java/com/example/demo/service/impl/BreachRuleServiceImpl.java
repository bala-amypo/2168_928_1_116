package com.example.demo.service.impl;

import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    @Override
    public String dummy() {
        return "Breach rule service working";
    }
}
