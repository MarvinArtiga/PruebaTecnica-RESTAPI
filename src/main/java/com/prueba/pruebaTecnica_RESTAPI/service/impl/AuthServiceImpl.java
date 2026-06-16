package com.prueba.pruebaTecnica_RESTAPI.service.impl;

import com.prueba.pruebaTecnica_RESTAPI.dto.auth.*;
import com.prueba.pruebaTecnica_RESTAPI.entity.User;
import com.prueba.pruebaTecnica_RESTAPI.repository.UserRepository;
import com.prueba.pruebaTecnica_RESTAPI.security.JwtService;
import com.prueba.pruebaTecnica_RESTAPI.service.AuthService;

import org.springframework.security.authentication.
        AuthenticationManager;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl
        implements AuthService {

    private final AuthenticationManager
            authenticationManager;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtService jwtService
    ) {
        this.authenticationManager =
                authenticationManager;

        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(
            LoginRequest request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user =
                userRepository.findByEmail(
                                request.getEmail())
                        .orElseThrow();

        String token =
                jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}