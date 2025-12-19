package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyCalculationController {

    @PostMapping("/calculate/{contractId}")
    public ResponseEntity<String> calculatePenalty(
            @PathVariable Long contractId) {
        return ResponseEntity.ok(
                "Penalty calculated for contract " + contractId
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCalculation(@PathVariable Long id) {
        return ResponseEntity.ok("Get penalty calculation " + id);
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<String> listCalculations(
            @PathVariable Long contractId) {
        return ResponseEntity.ok(
                "List penalty calculations for contract " + contractId
        );
    }
}
