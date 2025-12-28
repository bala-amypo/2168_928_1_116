@Service
@RequiredArgsConstructor
public class BreachReportServiceImpl implements BreachReportService {

    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final BreachReportRepository breachReportRepository;

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
}
