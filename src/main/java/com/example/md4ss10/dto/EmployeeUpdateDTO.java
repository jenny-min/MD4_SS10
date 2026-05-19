package com.example.md4ss10.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EmployeeUpdateDTO {
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, message = "Tên phải có ít nhất 5 ký tự")
    private String fullName;

    @Email(message = "Email không đúng định dạng")
    private String email;

    private String department;

    // optional
    private MultipartFile avatarFile;
}
