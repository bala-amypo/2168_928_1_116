package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;

    public ContractServiceImpl(
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo) {
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
    }

    @Override
    public Contract createContract(Contract contract) {
        return contractRepo.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = getContractById(id);
        contract.setId(existing.getId());
        return contractRepo.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepo.findAll();
    }

    @Override
    public Contract updateContractStatus(Long contractId) {
        Contract contract = getContractById(contractId);

        DeliveryRecord latest = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElse(null);

        if (latest != null && latest.getDeliveryDate().after(contract.getAgreedDeliveryDate())) {
            contract.setStatus("BREACHED");
        } else {
            contract.setStatus("COMPLETED");
        }

        return contractRepo.save(contract);
    }
}
