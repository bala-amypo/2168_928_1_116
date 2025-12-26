package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    // MUST be final for Lombok constructor injection
    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public Contract createContract(Contract contract) {

        // Double validation (NOT BigDecimal)
        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue() <= 0) {
            throw new BadRequestException("Invalid base contract value");
        }

        contract.setStatus("ACTIVE");
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract updateContract(Long id, Contract updated) {

        Contract existing = getContractById(id);

        existing.setTitle(updated.getTitle());
        existing.setCounterpartyName(updated.getCounterpartyName());
        existing.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        existing.setBaseContractValue(updated.getBaseContractValue());

        return contractRepository.save(existing);
    }

    @Override
    public void updateContractStatus(Long contractId) {

        Contract contract = getContractById(contractId);

        DeliveryRecord latest = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElse(null);

        if (latest == null) {
            contract.setStatus("ACTIVE");
        } else if (latest.getDeliveryDate()
                .isAfter(contract.getAgreedDeliveryDate())) {
            contract.setStatus("BREACHED");
        } else {
            contract.setStatus("COMPLETED");
        }

        contractRepository.save(contract);
    }
}
