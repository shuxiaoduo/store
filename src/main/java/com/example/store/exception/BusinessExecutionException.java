package com.example.store.exception;

public class BusinessExecutionException extends RuntimeException {

    private static final long serialVersionUID = 6032968939918828261L;

    public BusinessExecutionException(String message) {
        super(message);
    }
}
