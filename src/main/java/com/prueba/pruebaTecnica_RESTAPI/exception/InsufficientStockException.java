package com.prueba.pruebaTecnica_RESTAPI.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message) {
        super(message);
    }

}