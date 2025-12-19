package com.example.demo.service;

import com.example.demo.entity.Contract;
import java.util.List;

public interface ContractService {
    Contract save(Contract contract);
    Contract update(Long id, Contract contract);
    Contract getById(Long id);
    List<Contract> getAll();
    void delete(Long id);
}
