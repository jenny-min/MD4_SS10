package com.example.md4ss10.controllers;

import com.example.md4ss10.dto.UserLoginDTO;
import com.example.md4ss10.dto.UserRegisterDTO;
import com.example.md4ss10.models.User;
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

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRegisterDTO dto
    ){
        return userService.register(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }
}
