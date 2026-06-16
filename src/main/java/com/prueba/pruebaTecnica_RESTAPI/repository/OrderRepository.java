package com.prueba.pruebaTecnica_RESTAPI.repository;

import com.prueba.pruebaTecnica_RESTAPI.entity.Order;
import com.prueba.pruebaTecnica_RESTAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

}