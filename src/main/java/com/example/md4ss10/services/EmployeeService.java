package com.example.md4ss10.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.md4ss10.dto.EmployeeCreateDTO;
import com.example.md4ss10.models.Employee;
import com.example.md4ss10.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Employee create(EmployeeCreateDTO dto) {

        try {

            Map uploadResult = cloudinary.uploader().upload(
                    dto.getAvatarFile().getBytes(),
                    ObjectUtils.emptyMap()
            );

            String imageUrl = uploadResult.get("url").toString();

            Employee employee = new Employee();

            employee.setFullName(dto.getFullName());
            employee.setEmail(dto.getEmail());
            employee.setDepartment(dto.getDepartment());
            employee.setAvatarUrl(imageUrl);

            return employeeRepository.save(employee);

        } catch (Exception e) {

            throw new RuntimeException("Upload ảnh thất bại");
        }
    }
}
