package com.example.demo_back.Dto;
public class UniResponse {
    private boolean success;
    private String message;
    public UniResponse(){

    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}