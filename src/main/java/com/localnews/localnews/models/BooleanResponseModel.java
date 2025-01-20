package com.localnews.localnews.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BooleanResponseModel {
    private boolean status;
    private String message;
    private Object data;
    private Object metaData;

    public BooleanResponseModel() {
    }


    public BooleanResponseModel(boolean status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }


    public BooleanResponseModel(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }

    public BooleanResponseModel(boolean status, String message, Object data, Object metaData) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.metaData = metaData;

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getMetaData() {
        return metaData;
    }
}
