package com.example.demo.exception;

public class ValidateException extends RuntimeException {

    private String message;

    public ValidateException(String message) {
        super(message);
    }
}
