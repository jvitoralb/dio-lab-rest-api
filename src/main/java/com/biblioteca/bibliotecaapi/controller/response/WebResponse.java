package com.biblioteca.bibliotecaapi.controller.response;

import java.util.Date;

public class WebResponse {
    private String status;
    private Date timestamp;
    private Object message;

    public WebResponse(String status) {
        this.status = status;
        this.timestamp = new Date();
    }

    public WebResponse(String status, Object message) {
        this.status = status;
        this.timestamp = new Date();
        this.message = message;
    }

    public String getStatus() {
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
