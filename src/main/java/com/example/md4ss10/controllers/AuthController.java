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
    @ResponseBody
    public User register(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
       return userService.register(userRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }
}
