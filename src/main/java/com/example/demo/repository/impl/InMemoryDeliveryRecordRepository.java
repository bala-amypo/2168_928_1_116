package com.example.demo.repository.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;

import java.util.*;

public class InMemoryDeliveryRecordRepository implements DeliveryRecordRepository {

    private final Map<Long, DeliveryRecord> store = new HashMap<>();
    private long id = 1;

    @Override
    public DeliveryRecord save(DeliveryRecord record) {
        if (record.getId() == 0) {
            record.setId(id++);
        }
        store.put(record.getId(), record);
        return record;
    }

    @Override
    public Optional<DeliveryRecord> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<DeliveryRecord> findAll() {
        return new ArrayList<>(store.values());
    }
}
