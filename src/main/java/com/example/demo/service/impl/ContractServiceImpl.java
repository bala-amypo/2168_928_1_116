package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ContractService;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public Contract createContract(Contract c) {
        if (c.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be > 0");
        }
        return contractRepository.save(c);
    }

    @Override
    public Contract updateContract(Long id, Contract in) {
        Contract c = getContractById(id);
        c.setTitle(in.getTitle());
        c.setCounterpartyName(in.getCounterpartyName());
        c.setAgreedDeliveryDate(in.getAgreedDeliveryDate());
        c.setBaseContractValue(in.getBaseContractValue());
        return contractRepository.save(c);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract c = getContractById(id);
        Optional<DeliveryRecord> dr =
                deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id);

        if (dr.isPresent() &&
            dr.get().getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
            c.setStatus("BREACHED");
        } else {
            c.setStatus("ACTIVE");
        }
        contractRepository.save(c);
    }
}
