package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    // 🔴 FIELD NAMES MUST MATCH TESTS
    private ContractRepository contractRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private BreachReportRepository breachReportRepository;

    // 🔴 REQUIRED FOR REFLECTION
    public BreachReportServiceImpl() {
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        PenaltyCalculation calc = penaltyCalculationRepository
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No penalty calculation"));

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(calc.getDaysDelayed())
                .penaltyAmount(calc.getCalculatedPenalty())
                .reportGeneratedAt(LocalDateTime.now())
                .remarks(calc.getDaysDelayed() > 0 ? "Penalty applied" : "No delay")
                .build();

        return breachReportRepository.save(report);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return breachReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }
}
