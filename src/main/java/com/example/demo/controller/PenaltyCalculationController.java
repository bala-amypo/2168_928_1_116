package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyCalculationController {

    @PostMapping("/calculate/{contractId}")
    public String calculatePenalty(@PathVariable Long contractId) {
        return "Penalty calculated for contract " + contractId;
    }

    @GetMapping("/{id}")
    public String getCalculation(@PathVariable Long id) {
        return "Get penalty calculation " + id;
    }

    @GetMapping("/contract/{contractId}")
    public String listCalculations(@PathVariable Long contractId) {
        return "List penalty calculations for contract " + contractId;
    }
}
