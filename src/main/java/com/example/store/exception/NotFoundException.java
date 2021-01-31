package com.example.store.exception;

public class NotFoundException extends BusinessExecutionException {

    private static final long serialVersionUID = 5137581007469529724L;

    public NotFoundException(String message) {
        super(message);
    }
}
