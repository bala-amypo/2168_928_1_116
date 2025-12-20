package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔴 THIS WAS MISSING
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository reportRepo;
    private final ContractRepository contractRepo;
    private final PenaltyCalculationRepository penaltyRepo;

    public BreachReportServiceImpl(
            BreachReportRepository reportRepo,
            ContractRepository contractRepo,
            PenaltyCalculationRepository penaltyRepo) {
        this.reportRepo = reportRepo;
        this.contractRepo = contractRepo;
        this.penaltyRepo = penaltyRepo;
    }

    @Override
    public BreachReport generateReport(Long contractId, Long penaltyCalculationId) {

        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calculation = penaltyRepo.findById(penaltyCalculationId)
                .orElseThrow(() -> new ResourceNotFoundException("Penalty calculation not found"));

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setPenaltyCalculation(calculation);
        report.setDaysDelayed(calculation.getDaysDelayed());
        report.setPenaltyAmount(calculation.getCalculatedPenalty());
        report.setRemarks("Penalty calculated successfully");

        return reportRepo.save(report);
    }

    @Override
    public List<BreachReport> getReportsByContract(Long contractId) {
        return reportRepo.findByContractId(contractId);
    }
}
