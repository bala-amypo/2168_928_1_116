package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository repo;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {
        return repo.save(record);
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return repo.findByContractIdOrderByDeliveryDateAsc(contractId);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return repo.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new BadRequestException("No delivery record"));
    }
}
