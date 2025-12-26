package com.example.demo.service;

import com.example.demo.entity.Contract;
import java.util.List;

public interface ContractService {

    Contract create(Contract contract);

    List<Contract> getAll();

    Contract getById(Long id);
}
