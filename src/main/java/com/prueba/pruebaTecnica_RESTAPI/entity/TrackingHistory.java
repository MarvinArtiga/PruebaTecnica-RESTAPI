package com.prueba.pruebaTecnica_RESTAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.pruebaTecnica_RESTAPI.enums.OrderStatus;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tracking_history")
public class TrackingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime timestamp;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public TrackingHistory() {
    }

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Order getOrder() {
        return order;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}