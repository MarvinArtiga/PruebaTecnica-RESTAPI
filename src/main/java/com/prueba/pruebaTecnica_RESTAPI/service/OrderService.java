package com.prueba.pruebaTecnica_RESTAPI.service;

import com.prueba.pruebaTecnica_RESTAPI.dto.order.CreateOrderRequest;
import com.prueba.pruebaTecnica_RESTAPI.entity.Order;
import com.prueba.pruebaTecnica_RESTAPI.entity.TrackingHistory;
import com.prueba.pruebaTecnica_RESTAPI.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    Order createOrder(
            String email,
            CreateOrderRequest request
    );

    List<Order> getMyOrders(
            String email
    );

    List<TrackingHistory> getTracking(
            Long orderId
    );

    void updateStatus(
            Long orderId,
            OrderStatus status
    );
}