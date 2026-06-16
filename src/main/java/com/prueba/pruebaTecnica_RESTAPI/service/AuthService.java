package com.prueba.pruebaTecnica_RESTAPI.service;

import com.prueba.pruebaTecnica_RESTAPI.dto.auth.LoginRequest;
import com.prueba.pruebaTecnica_RESTAPI.dto.auth.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}