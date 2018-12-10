package com.example.demo.exception;

public class AppException extends RuntimeException {

    private String message;

    public AppException(String message) {
        super(message);
    }
}
