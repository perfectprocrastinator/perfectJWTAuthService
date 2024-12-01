package com.example.perfectuserservice.exceptions;

public class UserAlreadyPresentException extends RuntimeException{
    public UserAlreadyPresentException(String message){
        super(message);
    }
}
