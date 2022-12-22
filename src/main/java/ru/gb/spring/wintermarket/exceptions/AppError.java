package ru.gb.spring.wintermarket.exceptions;

public class AppError {
    private int statusCode;
    private String message;

    public int getStatusCode(){
        return statusCode;
    }
    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public AppError(){}
    public AppError(int statusCode, String message){
        this.message = message;
        this.statusCode = statusCode;
    }

}

