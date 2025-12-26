package com.example.demo.repository;

import com.example.demo.entity.Contract;
import java.util.*;

public interface ContractRepository {
    Contract save(Contract contract);
    Optional<Contract> findById(long id);
    List<Contract> findAll();
}
