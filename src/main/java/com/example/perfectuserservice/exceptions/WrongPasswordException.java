package com.example.perfectuserservice.exceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(String message){
        super(message);
    }
}
