package com.example.demo.service;

import com.example.demo.entity.*;

import java.math.BigDecimal;

public class ProjectValidator {

    /* ================= CONTRACT ================= */

    public static void validateContract(Contract contract) {

        if (contract == null) {
            throw new RuntimeException("Contract cannot be null");
        }

        if (contract.getContractNumber() == null || contract.getContractNumber().isBlank()) {
            throw new RuntimeException("Contract number is required");
        }

        if (contract.getAgreedDeliveryDate() == null) {
            throw new RuntimeException("Agreed delivery date is required");
        }

        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Base contract value must be greater than zero");
        }
    }

    /* ================= DELIVERY ================= */

    public static void validateDeliveryRecord(DeliveryRecord record) {

        if (record == null) {
            throw new RuntimeException("Delivery record cannot be null");
        }

        if (record.getContract() == null) {
            throw new RuntimeException("Contract is required");
        }

        if (record.getDeliveryDate() == null) {
            throw new RuntimeException("Delivery date is required");
        }
    }

    /* ================= BREACH RULE ================= */

    public static void validateBreachRule(BreachRule rule) {

        if (rule == null) {
            throw new RuntimeException("Rule cannot be null");
        }

        if (rule.getRuleName() == null || rule.getRuleName().isBlank()) {
            throw new RuntimeException("Rule name is required");
        }

        if (rule.getPenaltyPerDay() == null ||
                rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Penalty per day must be positive");
        }

        if (rule.getMaxPenaltyPercentage() == null ||
                rule.getMaxPenaltyPercentage() <= 0 ||
                rule.getMaxPenaltyPercentage() > 100) {
            throw new RuntimeException("Invalid max penalty percentage");
        }
    }
}
