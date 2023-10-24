package com.biblioteca.bibliotecaapi.controller.response;

import java.util.Date;

public class ResponseBody {
    private int status;
    private Date date;
    private Object message;

    public ResponseBody(int status) {
        this.status = status;
        this.date = new Date();
    }

    public int getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
