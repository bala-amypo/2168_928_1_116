package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    @PostMapping("/generate/{contractId}")
    public String generateReport(@PathVariable Long contractId) {
        return "Breach report generated for contract " + contractId;
    }

    @GetMapping("/{id}")
    public String getReport(@PathVariable Long id) {
        return "Get breach report " + id;
    }

    @GetMapping("/contract/{contractId}")
    public String listReportsByContract(@PathVariable Long contractId) {
        return "List breach reports for contract " + contractId;
    }

    @GetMapping
    public String listAllReports() {
        return "List all breach reports";
    }
}
