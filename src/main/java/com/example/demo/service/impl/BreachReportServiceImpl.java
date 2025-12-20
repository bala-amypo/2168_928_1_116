package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;

import java.util.List;

public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository reportRepo;
    private final PenaltyCalculationRepository calcRepo;

    public BreachReportServiceImpl(
            BreachReportRepository reportRepo,
            PenaltyCalculationRepository calcRepo) {
        this.reportRepo = reportRepo;
        this.calcRepo = calcRepo;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        PenaltyCalculation calc = calcRepo
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new BadRequestException("No penalty calculation"));

        BreachReport report = new BreachReport();
        report.setContract(calc.getContract());
        report.setDaysDelayed(calc.getDaysDelayed());
        report.setPenaltyAmount(calc.getCalculatedPenalty());
        report.setRemarks("Auto generated");

        return reportRepo.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
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
