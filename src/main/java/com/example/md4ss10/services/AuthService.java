package com.example.md4ss10.services;

import com.example.md4ss10.dto.request.LoginRequestDTO;
import com.example.md4ss10.dto.response.LoginResponseDTO;
import com.example.md4ss10.securities.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public LoginResponseDTO login(LoginRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtProvider.generateToken(authentication);

        return new LoginResponseDTO(
                authentication.getName(),
                authentication.getAuthorities().toString(),
                token
        );
    }
}
