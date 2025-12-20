package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @PostMapping
    public Contract create(@RequestBody Contract contract) {
        return service.createContract(contract);
    }

    @PutMapping("/{id}")
    public Contract update(@PathVariable Long id, @RequestBody Contract contract) {
        return service.updateContract(id, contract);
    }

    @GetMapping("/{id}")
    public Contract get(@PathVariable Long id) {
        return service.getContractById(id);
    }

    @GetMapping
    public List<Contract> getAll() {
        return service.getAllContracts();
    }

    @PutMapping("/{id}/update-status")
    public Contract updateStatus(@PathVariable Long id) {
        return service.updateContractStatus(id);
    }
}
