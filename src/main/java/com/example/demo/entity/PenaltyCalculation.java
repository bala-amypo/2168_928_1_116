@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne
    private BreachRule breachRule;

    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;

    private LocalDateTime calculatedAt;

    @PrePersist
    void onCalc() {
        calculatedAt = LocalDateTime.now();
    }
}
