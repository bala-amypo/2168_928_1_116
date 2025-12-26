package com.example.demo.repository;

import com.example.demo.entity.BreachReport;
import java.util.*;

public interface BreachReportRepository {
    BreachReport save(BreachReport report);
    Optional<BreachReport> findById(long id);
    List<BreachReport> findAll();
}
