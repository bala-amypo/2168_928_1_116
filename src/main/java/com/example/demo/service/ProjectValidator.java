package com.example.demo.service.validation;

import com.example.demo.entity.*;

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

        if (contract.getBaseContractValue() == null) {
            throw new RuntimeException("Base contract value is required");
        }
    }

    /* ================= DELIVERY RECORD ================= */

    public static void validateDeliveryRecord(DeliveryRecord record) {

        if (record == null) {
            throw new RuntimeException("Delivery record cannot be null");
        }

        if (record.getContract() == null) {
            throw new RuntimeException("Contract is mandatory for delivery record");
        }

        if (record.getDeliveryDate() == null) {
            throw new RuntimeException("Delivery date is required");
        }
    }

    /* ================= BREACH RULE ================= */

    public static void validateBreachRule(BreachRule rule) {

        if (rule == null) {
            throw new RuntimeException("Breach rule cannot be null");
        }

        if (rule.getRuleName() == null || rule.getRuleName().isBlank()) {
            throw new RuntimeException("Rule name is required");
        }

        if (rule.getPenaltyPerDay() == null) {
            throw new RuntimeException("Penalty per day is required");
        }

        if (rule.getMaxPenaltyPercentage() == null) {
            throw new RuntimeException("Max penalty percentage is required");
        }
    }

    /* ================= PENALTY CALCULATION ================= */

    public static void validatePenaltyCalculation(PenaltyCalculation calc) {

        if (calc == null) {
            throw new RuntimeException("Penalty calculation cannot be null");
        }

        if (calc.getContract() == null) {
            throw new RuntimeException("Contract is required for penalty calculation");
        }

        if (calc.getDaysDelayed() < 0) {
            throw new RuntimeException("Days delayed cannot be negative");
        }
    }

    /* ================= BREACH REPORT ================= */

    public static void validateBreachReport(BreachReport report) {

        if (report == null) {
            throw new RuntimeException("Breach report cannot be null");
        }

        if (report.getContract() == null) {
            throw new RuntimeException("Contract is required for breach report");
        }

        if (report.getPenaltyAmount() == null) {
            throw new RuntimeException("Penalty amount is required");
        }
    }

    /* ================= USER ================= */

    public static void validateUser(User user) {

        if (user == null) {
            throw new RuntimeException("User cannot be null");
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new RuntimeException("Username is required");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new RuntimeException("Email is required");
        }

        if (user.getRole() == null || user.getRole().isBlank()) {
            throw new RuntimeException("Role is required");
        }
    }
}
