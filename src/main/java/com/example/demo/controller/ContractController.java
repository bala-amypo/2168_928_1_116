package com.example.demo.controller;

import com.example.demo.entity.Contract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @PostMapping
    public ResponseEntity<Contract> createContract(
            @RequestBody Contract contract) {
        return ResponseEntity.ok(contract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(
            @PathVariable Long id,
            @RequestBody Contract contract) {
        contract.setId(id);
        return ResponseEntity.ok(contract);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getContract(@PathVariable Long id) {
        return ResponseEntity.ok("Get contract " + id);
    }

    @GetMapping
    public ResponseEntity<String> listContracts() {
        return ResponseEntity.ok("List all contracts");
    }

    @PutMapping("/{id}/update-status")
    public ResponseEntity<String> recomputeStatus(@PathVariable Long id) {
        return ResponseEntity.ok("Recompute status " + id);
    }
}
