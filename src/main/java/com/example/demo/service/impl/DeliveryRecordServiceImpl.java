package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository repository;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeliveryRecord save(DeliveryRecord record) {
        return repository.save(record);
    }

    @Override
    public List<DeliveryRecord> getByContract(Long contractId) {
        return repository.findByContractIdOrderByDeliveryDateAsc(contractId);
    }
}
