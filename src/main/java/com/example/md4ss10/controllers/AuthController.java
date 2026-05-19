package com.example.md4ss10.controllers;

import com.example.md4ss10.dto.UserRegisterDTO;
import com.example.md4ss10.dto.request.LoginRequestDTO;
import com.example.md4ss10.dto.response.LoginResponseDTO;
import com.example.md4ss10.services.AuthService;
import com.example.md4ss10.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService  authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRegisterDTO dto
    ){
        return userService.register(dto);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO request
    ) {
        return authService.login(request);
    }
}
