package com.example.demo.repository.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;

import java.util.*;

public class InMemoryBreachRuleRepository implements BreachRuleRepository {

    private final Map<Long, BreachRule> store = new HashMap<>();
    private long id = 1;

    @Override
    public BreachRule save(BreachRule rule) {
        if (rule.getId() == 0) {
            rule.setId(id++);
        }
        store.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public Optional<BreachRule> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<BreachRule> findAll() {
        return new ArrayList<>(store.values());
    }
}
