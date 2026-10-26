package com.aditya.ems.controller;

import com.aditya.ems.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final List<Employee> employees = List.of(
            new Employee(1L, "Arjun Nair", "arjun@example.com", "Engineering"),
            new Employee(2L, "Meera Menon", "meera@example.com", "Finance"),
            new Employee(3L, "Rahul Menon", "rahul@example.com", "Human Resources")
    );

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        for (Employee employee : employees) {

            if (employee.getId().equals(id)) {
                return ResponseEntity.ok(employee);
            }

        }

        return ResponseEntity.notFound().build();
    }
}