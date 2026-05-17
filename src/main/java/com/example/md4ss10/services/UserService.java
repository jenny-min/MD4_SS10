package com.example.md4ss10.services;

import com.example.md4ss10.dto.UserLoginDTO;
import com.example.md4ss10.dto.UserRegisterDTO;
import com.example.md4ss10.models.User;
import com.example.md4ss10.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO userRegisterDTO) {
        // check username tồn tại
        if (userRepository.existsByUsername(userRegisterDTO.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setEmail(userRegisterDTO.getEmail());
        user.setFullName(userRegisterDTO.getFullName());
        return userRepository.save(user);
    }

    public ResponseEntity<?> login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername()).orElse(null);
        if (user != null && passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }else {
            return new ResponseEntity<>("Username hoặc mật khẩu không đúng", HttpStatus.BAD_REQUEST);
        }
    }
}
