package com.prueba.pruebaTecnica_RESTAPI.dto.order;

import java.util.List;

public class CreateOrderRequest {

    private List<OrderItemRequest> items;

    public CreateOrderRequest() {
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}