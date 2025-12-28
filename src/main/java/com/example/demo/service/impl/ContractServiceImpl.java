package com.example.demo.service;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        contract.setId(null); // VERY IMPORTANT — let DB generate ID
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));

        existing.setContractNumber(contract.getContractNumber());
        existing.setTitle(contract.getTitle());
        existing.setCounterpartyName(contract.getCounterpartyName());
        existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        existing.setBaseContractValue(contract.getBaseContractValue());
        existing.setStatus(contract.getStatus());

        return contractRepository.save(existing);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));

        contract.setStatus("UPDATED");
        contractRepository.save(contract);
    }
}
