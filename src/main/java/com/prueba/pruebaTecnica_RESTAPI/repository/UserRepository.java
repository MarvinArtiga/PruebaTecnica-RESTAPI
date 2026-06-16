package com.prueba.pruebaTecnica_RESTAPI.repository;

import com.prueba.pruebaTecnica_RESTAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}