package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {

    @Autowired
    MyRepo repo;

    // Home / API status
    @GetMapping("/")
    public String home() {
        return "StudentSphere API is running successfully!";
    }

    // Show all students
    @GetMapping("/students")
    public List<Student> show() {
        return (List<Student>) repo.findAll();
    }

    // Show student by ID
    @GetMapping("/students/{id}")
    public Optional<Student> showById(@PathVariable("id") int id) {
        return repo.findById(id);
    }

    // Show students by name
    @GetMapping("/students/name/{name}")
    public List<Student> showByName(@PathVariable("name") String name) {
        return repo.findByName(name);
    }

    // Add student
    @PostMapping("/addstudent")
    public Student addStudent(@RequestBody Student s) {
        return repo.save(s);
    }

    // Delete student by ID
    @DeleteMapping("/deletestudent/{id}")
    public String deleteById(@PathVariable("id") int id) {

        Optional<Student> s = repo.findById(id);

        if (s.isPresent()) {
            repo.deleteById(id);
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }

    // Update student by ID
    @PutMapping("/updatestudent/{id}")
    public Student updateById(
            @PathVariable("id") int id,
            @RequestBody Student s1) {

        Optional<Student> existingStudent = repo.findById(id);

        if (existingStudent.isPresent()) {
            s1.setId(id);
            return repo.save(s1);
        }

        return null;
    }
}