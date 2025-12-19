package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {


    @PostMapping
    public String createRule() {
        return "Breach rule created";
    }

    @PutMapping("/{id}")
    public String updateRule(@PathVariable Long id) {
        return "Breach rule updated " + id;
    }

    @GetMapping("/{id}")
    public String getRule(@PathVariable Long id) {
        return "Get breach rule " + id;
    }

    @GetMapping
    public String listRules() {
        return "List all breach rules";
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateRule(@PathVariable Long id) {
        return "Breach rule deactivated " + id;
    }
}
