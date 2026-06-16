package com.prueba.pruebaTecnica_RESTAPI.controller;

import com.prueba.pruebaTecnica_RESTAPI.dto.order.CreateOrderRequest;
import com.prueba.pruebaTecnica_RESTAPI.dto.tracking.UpdateStatusRequest;
import com.prueba.pruebaTecnica_RESTAPI.entity.Order;
import com.prueba.pruebaTecnica_RESTAPI.service.OrderService;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            Authentication authentication,
            @RequestBody CreateOrderRequest request
    ) {

        return ResponseEntity.ok(
                orderService.createOrder(
                        authentication.getName(),
                        request
                )
        );
    }

    @GetMapping("/my-orders")
    public ResponseEntity<?> myOrders(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                orderService.getMyOrders(
                        authentication.getName()
                )
        );
    }

    @GetMapping("/tracking/{orderId}")
    public ResponseEntity<?> tracking(
            @PathVariable Long orderId
    ) {

        return ResponseEntity.ok(
                orderService.getTracking(orderId)
        );
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateStatusRequest request
    ) {

        orderService.updateStatus(
                orderId,
                request.getStatus()
        );

        return ResponseEntity.ok(
                "Estado actualizado"
        );
    }
}