package com.example.md4ss10.services;

import com.example.md4ss10.dto.UserLoginDTO;
import com.example.md4ss10.dto.UserRegisterDTO;
import com.example.md4ss10.models.User;
import com.example.md4ss10.repositories.UserRepository;
import com.example.md4ss10.securities.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public ResponseEntity<?> register(UserRegisterDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {

            return ResponseEntity
                    .badRequest()
                    .body("Username đã tồn tại");
        }

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        user.setEmail(dto.getEmail());

        user.setFullName(dto.getFullName());

        user.setRole("USER");

        user.setEnabled(true);

        userRepository.save(user);

        return ResponseEntity.ok("Register success");
    }

    public ResponseEntity<?> login(UserLoginDTO dto){

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    dto.getUsername(),
                                    dto.getPassword()
                            )
                    );

            User user = userRepository
                    .findByUsername(dto.getUsername())
                    .orElseThrow();

            String token = jwtProvider.generateToken(user);

            return ResponseEntity.ok(
                    Map.of(
                            "accessToken", token,
                            "type", "Bearer",
                            "username", user.getUsername()
                    )
            );

        } catch (BadCredentialsException e){

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    "username or password incorrect"
                            )
                    );
        }
    }
}
