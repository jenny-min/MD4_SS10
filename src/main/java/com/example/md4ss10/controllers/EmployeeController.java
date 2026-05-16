package com.example.md4ss10.controllers;

import com.example.md4ss10.models.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @GetMapping()
    @ResponseBody
    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1,"Nguyễn Văn A", 5000));
        employees.add(new Employee(2,"Phạm Văn B",5000));
        employees.add(new Employee(3,"Nguyễn Văn C",3000));

        return employees;
    }
}
