package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Mycontroller {

    @Autowired
    MyRepo repo;

    // Show all students
    @RequestMapping("/students")
    @ResponseBody
    public List<Student> show() {
        return (List<Student>) repo.findAll();
    }

    // Show student by ID
    @RequestMapping("/students/{id}")
    @ResponseBody
    public Optional<Student> showById(@PathVariable("id") int id) {
        return repo.findById(id);
    }

    // Show students by name
    @RequestMapping("/students/name/{name}")
    @ResponseBody
    public List<Student> showByName(@PathVariable("name") String name) {
        return repo.findByName(name);
    }

    // Add student
    @RequestMapping("/addstudent")
    @ResponseBody
    public Student addStudent(@RequestBody Student s) {
        return repo.save(s);
    }

    // Delete student by ID
    @RequestMapping("/deletestudent/{id}")
    @ResponseBody
    public String deleteById(@PathVariable("id") int id) {

        Optional<Student> s = repo.findById(id);

        if (s.isPresent()) {
            repo.deleteById(id);
            return "deleted";
        } else {
            return "not found";
        }
    }

    // Update student by ID
    @RequestMapping("/updatestudent/{id}")
    @ResponseBody
    public Student updateById(
            @PathVariable("id") int id,
            @RequestBody Student s1) {

        Optional<Student> s = repo.findById(id);

        if (s.isPresent()) {
            s1.setId(id);
            repo.save(s1);
            return s1;
        } else {
            return null;
        }
    }
}