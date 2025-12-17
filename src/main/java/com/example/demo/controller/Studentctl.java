package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Studententity;
import com.example.demo.service.Studentservice;

@RestController
@RequestMapping("/student")
public class Studentctl {

    @Autowired
    privateStudentservice ser;

    //post
    @PostMapping("/add")
    public Studententity addStudent(@RequestBody studententity st){
        return ser.saveData(st);
    }

    //GET ALL
    @GetMapping("/getall")
    public Collection<Studententity> getAllStudents(){
        return ser.saveData(st);
    }

    //GET ALL
    @GetMapping("/get/{id}")
    public Studententity getStudentById(@PathVariable int id){
        return ser.getById(id);
    }

    //PUT(UPDATE)
    @PutMapping("/update/{id}")
    public Studententity updateStudent(
        @PathVariable int id,
        @RequestBody Studententity st) {
            return ser.update(id, st);
        }
}

