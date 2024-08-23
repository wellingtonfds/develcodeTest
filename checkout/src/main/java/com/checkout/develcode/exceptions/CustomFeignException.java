package com.checkout.develcode.exceptions;

public class CustomFeignException extends RuntimeException {

    private final int statusCode;

    public CustomFeignException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
