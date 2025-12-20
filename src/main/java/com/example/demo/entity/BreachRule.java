package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "breach_rule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Rule name is required")
    private String ruleName;

    @NotNull(message = "Penalty per day is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal penaltyPerDay;

    @NotNull(message = "Max penalty percentage is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private BigDecimal maxPenaltyPercentage;

    private boolean active = true;

    private boolean isDefaultRule;
}
