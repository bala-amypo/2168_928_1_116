package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Studententity;

public interface StudentService {
    studententity saveData(Studententity st);
    Studententity insertStudententity(Studententity newStudent);
    List<Studententity>getOneStudent(Long id);
    void deleteStudent(Long id);
    Student insertStudententity
}