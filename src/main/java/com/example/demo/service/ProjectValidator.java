package com.example.demo.service;

import com.example.demo.entity.*;

import java.math.BigDecimal;

public class ProjectValidator {

    public static void validateUser(User user) {
        if (user == null) throw new RuntimeException("User cannot be null");
        if (user.getUsername() == null || user.getUsername().isBlank())
            throw new RuntimeException("Username required");
        if (user.getEmail() == null || user.getEmail().isBlank())
            throw new RuntimeException("Email required");
        if (user.getRole() == null || user.getRole().isBlank())
            throw new RuntimeException("Role required");
    }

    public static void validateContract(Contract contract) {
        if (contract == null) throw new RuntimeException("Contract null");
        if (contract.getContractNumber() == null || contract.getContractNumber().isBlank())
            throw new RuntimeException("Contract number required");
        if (contract.getAgreedDeliveryDate() == null)
            throw new RuntimeException("Delivery date required");
        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0)
            throw new RuntimeException("Invalid contract value");
    }

    public static void validateBreachRule(BreachRule rule) {
        if (rule == null) throw new RuntimeException("Rule null");
        if (rule.getRuleName() == null || rule.getRuleName().isBlank())
            throw new RuntimeException("Rule name required");
        if (rule.getPenaltyPerDay() == null ||
                rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0)
            throw new RuntimeException("Penalty must be positive");
        if (rule.getMaxPenaltyPercentage() == null ||
                rule.getMaxPenaltyPercentage() <= 0 ||
                rule.getMaxPenaltyPercentage() > 100)
            throw new RuntimeException("Invalid percentage");
    }
}
