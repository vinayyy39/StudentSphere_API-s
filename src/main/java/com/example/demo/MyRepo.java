package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MyRepo extends CrudRepository<Student, Integer> {

    List<Student> findByName(String name);
}