package com.example.demo.repository.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;

import java.util.*;

public class InMemoryBreachReportRepository implements BreachReportRepository {

    private final Map<Long, BreachReport> store = new HashMap<>();
    private long id = 1;

    @Override
    public BreachReport save(BreachReport report) {
        if (report.getId() == 0) {
            report.setId(id++);
        }
        store.put(report.getId(), report);
        return report;
    }

    @Override
    public Optional<BreachReport> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<BreachReport> findAll() {
        return new ArrayList<>(store.values());
    }
}
