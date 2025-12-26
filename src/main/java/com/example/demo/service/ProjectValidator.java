package com.example.demo.service;

import com.example.demo.entity.*;

import java.math.BigDecimal;

public class ProjectValidator {

    public static void validateContract(Contract contract) {
        if (contract == null)
            throw new RuntimeException("Contract cannot be null");

        if (contract.getContractNumber() == null || contract.getContractNumber().isBlank())
            throw new RuntimeException("Contract number is required");

        if (contract.getAgreedDeliveryDate() == null)
            throw new RuntimeException("Agreed delivery date is required");

        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0)
            throw new RuntimeException("Base contract value must be greater than zero");
    }

    public static void validateDeliveryRecord(DeliveryRecord record) {
        if (record == null)
            throw new RuntimeException("Delivery record cannot be null");

        if (record.getContract() == null)
            throw new RuntimeException("Contract is required");

        if (record.getDeliveryDate() == null)
            throw new RuntimeException("Delivery date is required");
    }

    public static void validateBreachRule(BreachRule rule) {
        if (rule == null)
            throw new RuntimeException("Breach rule cannot be null");

        if (rule.getRuleName() == null || rule.getRuleName().isBlank())
            throw new RuntimeException("Rule name is required");

        if (rule.getPenaltyPerDay() <= 0)
            throw new RuntimeException("Penalty per day must be positive");

        if (rule.getMaxPenaltyPercentage() <= 0 ||
                rule.getMaxPenaltyPercentage() > 100)
            throw new RuntimeException("Invalid max penalty percentage");
    }

    public static void validatePenaltyCalculation(PenaltyCalculation calc) {
        if (calc == null)
            throw new RuntimeException("Penalty calculation cannot be null");

        if (calc.getContract() == null)
            throw new RuntimeException("Contract is required");

        if (calc.getDaysDelayed() < 0)
            throw new RuntimeException("Days delayed cannot be negative");
    }

    public static void validateBreachReport(BreachReport report) {
        if (report == null)
            throw new RuntimeException("Breach report cannot be null");

        if (report.getContract() == null)
            throw new RuntimeException("Contract is required");

        if (report.getPenaltyAmount() == null ||
                report.getPenaltyAmount().compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Penalty amount must be non-negative");
    }

    public static void validateUser(User user) {
        if (user == null)
            throw new RuntimeException("User cannot be null");

        if (user.getEmail() == null || user.getEmail().isBlank())
            throw new RuntimeException("Email is required");

        if (user.getPassword() == null || user.getPassword().isBlank())
            throw new RuntimeException("Password is required");

        if (user.getRole() == null || user.getRole().isBlank())
            throw new RuntimeException("Role is required");
    }
}
