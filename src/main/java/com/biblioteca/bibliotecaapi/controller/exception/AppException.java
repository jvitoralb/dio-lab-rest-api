package com.biblioteca.bibliotecaapi.controller.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    private HttpStatus statusCode;

    public AppException() {}

    public AppException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
