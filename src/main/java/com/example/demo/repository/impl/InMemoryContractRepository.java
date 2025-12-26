package com.example.demo.repository.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;

import java.util.*;

public class InMemoryContractRepository implements ContractRepository {

    private final Map<Long, Contract> store = new HashMap<>();
    private long id = 1;

    @Override
    public Contract save(Contract contract) {
        if (contract.getId() == 0) {
            contract.setId(id++);
        }
        store.put(contract.getId(), contract);
        return contract;
    }

    @Override
    public Optional<Contract> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Contract> findAll() {
        return new ArrayList<>(store.values());
    }
}
