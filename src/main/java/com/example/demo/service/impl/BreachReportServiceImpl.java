package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository reportRepository;
    private final PenaltyCalculationRepository calculationRepository;

    public BreachReportServiceImpl(BreachReportRepository reportRepository,
                                   PenaltyCalculationRepository calculationRepository) {
        this.reportRepository = reportRepository;
        this.calculationRepository = calculationRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {
        PenaltyCalculation calc = calculationRepository
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));

        BreachReport report = new BreachReport();
        report.setContract(calc.getContract());
        report.setDaysDelayed(calc.getDaysDelayed());
        report.setPenaltyAmount(calc.getCalculatedPenalty());
        report.setRemarks("Auto generated");

        return reportRepository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return reportRepository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return reportRepository.findAll();
    }
}
