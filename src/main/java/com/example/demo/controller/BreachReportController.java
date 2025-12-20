package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-reports")
public class BreachReportController {

    private final BreachReportService service;

    public BreachReportController(BreachReportService service) {
        this.service = service;
    }

    // Generate report (requires contractId AND penaltyCalculationId)
    @PostMapping("/generate")
    public BreachReport generateReport(
            @RequestParam Long contractId,
            @RequestParam Long penaltyCalculationId) {

        return service.generateReport(contractId, penaltyCalculationId);
    }

    // Get report by id
    @GetMapping("/{id}")
    public BreachReport getReportById(@PathVariable Long id) {
        return service.getReportById(id);
    }

    // Get all reports for a contract
    @GetMapping("/contract/{contractId}")
    public List<BreachReport> getReportsByContract(
            @PathVariable Long contractId) {

        return service.getReportsByContract(contractId);
    }

    // Get all reports
    @GetMapping
    public List<BreachReport> getAllReports() {
        return service.getAllReports();
    }
}
