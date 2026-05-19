package com.example.md4ss10.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "salary")
    private double salary;

    private String avatarUrl;

    public void setEmail(String email) {
    }

    public void setDepartment(String department) {
    }
}
