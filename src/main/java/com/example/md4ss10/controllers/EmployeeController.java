package com.example.md4ss10.controllers;

import com.example.md4ss10.dto.EmployeeCreateDTO;
import com.example.md4ss10.dto.EmployeeUpdateDTO;
import com.example.md4ss10.models.Employee;
import com.example.md4ss10.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @Valid @ModelAttribute EmployeeUpdateDTO dto
    ) {

        return employeeService.update(id, dto);
    }
}
