package com.example.md4ss10.controllers;

import com.example.md4ss10.dto.EmployeeCreateDTO;
import com.example.md4ss10.models.Employee;
import com.example.md4ss10.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee create(
            @ModelAttribute EmployeeCreateDTO dto
    ) {

        return employeeService.create(dto);
    }
}
