package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;

import java.util.List;

public class BreachReportServiceImpl implements BreachReportService {

    private final PenaltyCalculationRepository calcRepo;
    private final BreachReportRepository reportRepo;
    private final ContractRepository contractRepo;

    public BreachReportServiceImpl(
            PenaltyCalculationRepository calcRepo,
            BreachReportRepository reportRepo,
            ContractRepository contractRepo) {
        this.calcRepo = calcRepo;
        this.reportRepo = reportRepo;
        this.contractRepo = contractRepo;
    }

    @Override
    public BreachReport generateReport(Long contractId) {
        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calc = calcRepo
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setDaysDelayed(calc.getDaysDelayed());
        report.setPenaltyAmount(calc.getCalculatedPenalty());
        report.setRemarks("Auto-generated breach report");

        return reportRepo.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return reportRepo.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return reportRepo.findAll();
    }
}
