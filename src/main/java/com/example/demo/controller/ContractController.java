package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @PostMapping
    public String createContract() {
        return "Create contract";
    }

    @PutMapping("/{id}")
    public String updateContract(@PathVariable Long id) {
        return "Update contract " + id;
    }

    @GetMapping("/{id}")
    public String getContract(@PathVariable Long id) {
        return "Get contract " + id;
    }

    @GetMapping
    public String listContracts() {
        return "List all contracts";
    }

    @PutMapping("/{id}/update-status")
    public String recomputeStatus(@PathVariable Long id) {
        return "Recompute status " + id;
    }
}
