package com.example.md4ss10.repositories;

import com.example.md4ss10.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
