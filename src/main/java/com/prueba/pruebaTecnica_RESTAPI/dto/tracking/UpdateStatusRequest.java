package com.prueba.pruebaTecnica_RESTAPI.dto.tracking;

import com.prueba.pruebaTecnica_RESTAPI.enums.OrderStatus;

public class UpdateStatusRequest {

    private OrderStatus status;

    public UpdateStatusRequest() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}