@Service
@RequiredArgsConstructor
public class BreachReportServiceImpl implements BreachReportService {

    private final ContractRepository contractRepository;
    private final BreachRuleRepository breachRuleRepository;
    private final BreachReportRepository breachReportRepository;

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("No active breach rule"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                LocalDate.now()
        );

        if (daysDelayed < 0) daysDelayed = 0;

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxPenalty = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxPenalty) > 0) {
            penalty = maxPenalty;
        }

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(daysDelayed)
                .penaltyAmount(penalty)
                .reportGeneratedAt(LocalDateTime.now())
                .remarks(daysDelayed > 0 ? "Penalty applied" : "No delay")
                .build();

        return breachReportRepository.save(report);
    }
}
