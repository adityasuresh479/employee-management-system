package com.aditya.ems.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.http.ResponseEntity;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnEmployees() {

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/employees", String.class);

        assertEquals(200, response.getStatusCode().value());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode employees = mapper.readTree(response.getBody());

        assertTrue(employees.isArray());
        assertEquals(3, employees.size());
        assertEquals("Arjun Nair", employees.get(0).get("name").asText());
    }

    @Test
    void shouldReturnEmployeeById() {

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/employees/2", String.class);

        assertEquals(200, response.getStatusCode().value());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode employee = mapper.readTree(response.getBody());

        assertEquals(2L, employee.get("id").asLong());
        assertEquals("Meera Menon", employee.get("name").asText());
    }
    @Test
    void shouldReturn404WhenEmployeeDoesNotExist() {

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/employees/99", String.class);

        assertEquals(404, response.getStatusCode().value());
    }
}