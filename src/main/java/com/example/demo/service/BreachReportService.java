package com.example.demo.service;

import com.example.demo.entity.BreachReport;
import java.util.List;

public interface BreachReportService {

    BreachReport save(BreachReport report);
    List<BreachReport> getAll();
    BreachReport getById(Long id);
    void delete(Long id);
}
