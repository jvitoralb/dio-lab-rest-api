package com.biblioteca.bibliotecaapi.controller.exception;


import com.biblioteca.bibliotecaapi.controller.response.CustomResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CustomResponseBody> handleBusinessException(BusinessException e) {
        CustomResponseBody resErr = new CustomResponseBody(0);
        resErr.setMessage(e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(resErr);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CustomResponseBody> handleServerException(Throwable t) {
        CustomResponseBody resErr = new CustomResponseBody(-10);
        resErr.setMessage("Something went wrong.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resErr);
    }
}
