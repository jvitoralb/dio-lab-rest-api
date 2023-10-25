package com.biblioteca.bibliotecaapi.controller.response;

import java.util.Date;

public class ResponseBody {
    private int status;
    private Date timestamp;
    private Object message;

    public ResponseBody(int status) {
        this.status = status;
        this.timestamp = new Date();
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
