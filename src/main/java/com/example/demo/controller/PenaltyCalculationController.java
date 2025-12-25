package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyCalculationController {

    private final PenaltyCalculationService penaltyCalculationService;

    public PenaltyCalculationController(PenaltyCalculationService penaltyCalculationService) {
        this.penaltyCalculationService = penaltyCalculationService;
    }

    @PostMapping("/calculate/{contractId}")
    public ResponseEntity<PenaltyCalculation> calculatePenalty(
            @PathVariable Long contractId) {

        PenaltyCalculation calculation =
                penaltyCalculationService.calculatePenalty(contractId);

        return ResponseEntity.ok(calculation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenaltyCalculation> getCalculationById(@PathVariable Long id) {
        return ResponseEntity.ok(
                penaltyCalculationService.getCalculationById(id)
        );
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<PenaltyCalculation>> getByContract(
            @PathVariable Long contractId) {

        return ResponseEntity.ok(
                penaltyCalculationService.getCalculationsForContract(contractId)
        );
    }
}
