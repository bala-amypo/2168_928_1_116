package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    @PostMapping("/generate/{contractId}")
    public ResponseEntity<String> generateReport(@PathVariable Long contractId) {
        return ResponseEntity.ok(
                "Breach report generated for contract " + contractId
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getReport(@PathVariable Long id) {
        return ResponseEntity.ok("Get breach report " + id);
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<String> listReportsByContract(@PathVariable Long contractId) {
        return ResponseEntity.ok(
                "List breach reports for contract " + contractId
        );
    }

    @GetMapping
    public ResponseEntity<String> listAllReports() {
        return ResponseEntity.ok("List all breach reports");
    }
}
