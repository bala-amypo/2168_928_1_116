package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;

    public ContractServiceImpl(ContractRepository contractRepository,
                               DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract create(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract getById(Long id) {
        Contract c = contractRepository.findById(id).orElseThrow();

        List<DeliveryRecord> records =
                deliveryRecordRepository.findByContractIdOrderByDeliveryDateAsc(id);

        if (!records.isEmpty()) {
            LocalDate delivered = records.get(records.size() - 1).getDeliveryDate();
            if (delivered.isAfter(c.getAgreedDeliveryDate())) {
                c.setStatus("BREACHED");
            } else {
                c.setStatus("ON_TIME");
            }
        }

        return c;
    }
}
