package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

import java.time.LocalDate;
import java.util.List;

public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository repository;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {
        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("deliveryDate cannot be future");
        }
        return repository.save(record);
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return repository.findByContractIdOrderByDeliveryDateAsc(contractId);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return repository.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record"));
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery record not found"));
    }
}
