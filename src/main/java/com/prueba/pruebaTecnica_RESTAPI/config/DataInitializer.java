package com.prueba.pruebaTecnica_RESTAPI.config;

import com.prueba.pruebaTecnica_RESTAPI.entity.Product;
import com.prueba.pruebaTecnica_RESTAPI.entity.User;
import com.prueba.pruebaTecnica_RESTAPI.enums.Role;
import com.prueba.pruebaTecnica_RESTAPI.repository.ProductRepository;
import com.prueba.pruebaTecnica_RESTAPI.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            User admin = new User(
                    "admin@test.com",
                    passwordEncoder.encode("Admin123*"),
                    Role.ADMIN
            );

            User client = new User(
                    "client@test.com",
                    passwordEncoder.encode("Client123*"),
                    Role.CLIENT
            );

            userRepository.save(admin);
            userRepository.save(client);
        }

        if (productRepository.count() == 0) {

            productRepository.save(
                    new Product(
                            "Laptop Lenovo",
                            new BigDecimal("899.99"),
                            10
                    )
            );

            productRepository.save(
                    new Product(
                            "Mouse Logitech",
                            new BigDecimal("25.50"),
                            50
                    )
            );

            productRepository.save(
                    new Product(
                            "Teclado Mecánico",
                            new BigDecimal("75.00"),
                            20
                    )
            );
        }
    }
}