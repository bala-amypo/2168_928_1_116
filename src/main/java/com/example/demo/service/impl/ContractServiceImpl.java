package com.example.demo.service;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repository;

    public ContractServiceImpl(ContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contract createContract(Contract contract) {
        return repository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        return repository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Contract> getAllContracts() {
        return repository.findAll();
    }

    @Override
    public void updateContractStatus(Long contractId) {
        Contract c = repository.findById(contractId).orElse(null);
        if (c != null) {
            c.setStatus("UPDATED");
            repository.save(c);
        }
    }
}
