package com.aditya.ems.controller;

import com.aditya.ems.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping
    public List<Employee> getAllEmployees() {

        Employee emp1 = new Employee(
                1L, "Arjun Nair", "arjun@example.com", "Engineering"
        );

        Employee emp2 = new Employee(
                2L, "Meera Menon", "meera@example.com", "Finance"
        );

        Employee emp3 = new Employee(
                3L, "Rahul Menon", "rahul@example.com", "Human Resources"
        );

        return List.of(emp1, emp2, emp3);
    }
}