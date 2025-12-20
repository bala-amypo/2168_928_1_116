package com.example.demo.service;

import com.example.demo.entity.BreachReport;
import java.util.List;

public interface BreachReportService {

    BreachReport generateReport(Long contractId, Long penaltyCalculationId);

    BreachReport getReportById(Long id);

    List<BreachReport> getReportsByContract(Long contractId);

    List<BreachReport> getAllReports();
}
