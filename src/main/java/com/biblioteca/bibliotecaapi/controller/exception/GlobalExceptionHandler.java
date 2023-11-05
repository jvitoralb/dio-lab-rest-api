package com.biblioteca.bibliotecaapi.controller.exception;


import com.biblioteca.bibliotecaapi.controller.response.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<WebResponse> handleSuccessException(BusinessException e) {
        WebResponse resErr = new WebResponse("sucesso impedido");
        resErr.setMessage(e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(resErr);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<WebResponse> handleBusinessException(BusinessException e) {
        if (e.getStatusCode().equals(HttpStatus.OK)) {
            return handleSuccessException(e);
        }

        WebResponse resErr = new WebResponse("erro cliente");
        resErr.setMessage(e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(resErr);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<WebResponse> handleServerException(Throwable t) {
        WebResponse resErr = new WebResponse("erro servidor");
        resErr.setMessage("Something went wrong.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resErr);
    }
}
