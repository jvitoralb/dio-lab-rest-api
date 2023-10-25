package com.biblioteca.bibliotecaapi.controller.response;

import java.util.Date;

public class CustomResponseBody {
    private int status;
    private Date timestamp;
    private Object message;

    public CustomResponseBody(int status) {
        this.status = status;
        this.timestamp = new Date();
    }

    public CustomResponseBody(int status, Object message) {
        this.status = status;
        this.timestamp = new Date();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
