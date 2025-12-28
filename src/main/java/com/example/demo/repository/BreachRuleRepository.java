package com.example.demo.repository;

import com.example.demo.entity.BreachRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {

    // ⚠️ EXACT NAME REQUIRED BY TESTS
    Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
}
