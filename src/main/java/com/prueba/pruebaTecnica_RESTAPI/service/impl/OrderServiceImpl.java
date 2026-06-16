package com.prueba.pruebaTecnica_RESTAPI.service.impl;

import com.prueba.pruebaTecnica_RESTAPI.dto.order.CreateOrderRequest;
import com.prueba.pruebaTecnica_RESTAPI.dto.order.OrderItemRequest;
import com.prueba.pruebaTecnica_RESTAPI.entity.*;
import com.prueba.pruebaTecnica_RESTAPI.enums.OrderStatus;
import com.prueba.pruebaTecnica_RESTAPI.exception.InsufficientStockException;
import com.prueba.pruebaTecnica_RESTAPI.exception.ResourceNotFoundException;
import com.prueba.pruebaTecnica_RESTAPI.repository.OrderRepository;
import com.prueba.pruebaTecnica_RESTAPI.repository.ProductRepository;
import com.prueba.pruebaTecnica_RESTAPI.repository.TrackingHistoryRepository;
import com.prueba.pruebaTecnica_RESTAPI.repository.UserRepository;
import com.prueba.pruebaTecnica_RESTAPI.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final TrackingHistoryRepository trackingRepository;

    public OrderServiceImpl(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            TrackingHistoryRepository trackingRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.trackingRepository = trackingRepository;
    }

    @Override
    @Transactional
    public Order createOrder(
            String email,
            CreateOrderRequest request
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository
                    .findByIdForUpdate(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Producto no encontrado"));

            if (product.getStock() < itemRequest.getQuantity()) {
                throw new InsufficientStockException(
                        "Stock insuficiente para "
                                + product.getName());
            }

            product.setStock(
                    product.getStock()
                            - itemRequest.getQuantity()
            );

            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(product);

            item.setQuantity(itemRequest.getQuantity());

            item.setUnitPrice(product.getPrice());

            BigDecimal subtotal =
                    product.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            itemRequest.getQuantity()
                                    )
                            );

            item.setSubtotal(subtotal);

            total = total.add(subtotal);

            order.getItems().add(item);
        }

        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        TrackingHistory firstStatus =
                new TrackingHistory();

        firstStatus.setOrder(savedOrder);
        firstStatus.setStatus(OrderStatus.RECIBIDO);

        trackingRepository.save(firstStatus);

        return savedOrder;
    }

    @Override
    public List<Order> getMyOrders(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"));

        return orderRepository.findByUser(user);
    }

    @Override
    public List<TrackingHistory> getTracking(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pedido no encontrado"));

        return order.getTrackingHistory();
    }
}