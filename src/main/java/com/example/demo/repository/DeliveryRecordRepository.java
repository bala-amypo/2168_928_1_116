package com.example.demo.repository;

import com.example.demo.entity.DeliveryRecord;
import java.util.*;

public interface DeliveryRecordRepository {
    DeliveryRecord save(DeliveryRecord record);
    Optional<DeliveryRecord> findById(long id);
    List<DeliveryRecord> findAll();
}
