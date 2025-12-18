package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordService {
    DeliveryRecord save(DeliveryRecord record);
    List<DeliveryRecord> getByContract(Long contractId);
}
