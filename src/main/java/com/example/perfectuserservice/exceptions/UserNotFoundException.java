package com.example.perfectuserservice.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
