package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository repository;

    public BreachReportServiceImpl(BreachReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachReport generate(Contract contract,
                                 int daysDelayed,
                                 BigDecimal penalty) {

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(daysDelayed)
                .penaltyAmount(penalty)
                .build();

        return repository.save(report);
    }
}
