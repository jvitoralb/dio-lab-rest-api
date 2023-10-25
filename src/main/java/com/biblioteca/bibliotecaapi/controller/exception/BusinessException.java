package com.biblioteca.bibliotecaapi.controller.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends AppException {
    public BusinessException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BusinessException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
