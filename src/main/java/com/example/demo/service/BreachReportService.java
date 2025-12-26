package com.example.demo.service;

import com.example.demo.entity.Contract;
import com.example.demo.entity.BreachReport;
import java.math.BigDecimal;

public interface BreachReportService {

    BreachReport generate(
            Contract contract,
            int daysDelayed,
            BigDecimal penalty
    );
}
