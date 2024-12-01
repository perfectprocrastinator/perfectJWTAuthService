package com.example.perfectuserservice.advices;

import com.example.perfectuserservice.dtos.ExceptionDTO;
import com.example.perfectuserservice.exceptions.UserAlreadyPresentException;
import com.example.perfectuserservice.exceptions.UserNotFoundException;
import com.example.perfectuserservice.exceptions.WrongPasswordException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(UserAlreadyPresentException.class)
    public ExceptionDTO handleUserAlreadyPresentException(UserAlreadyPresentException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(ex.getMessage());
        exceptionDTO.setError("Please Enter new User");
        return exceptionDTO;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionDTO handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(ex.getMessage());
        exceptionDTO.setError("User Trying to Log in does not exist");
        return exceptionDTO;
    }
    @ExceptionHandler(WrongPasswordException.class)
    public ExceptionDTO handleWrongPasswordException(WrongPasswordException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(ex.getMessage());
        exceptionDTO.setError("Please enter correct password");
        return exceptionDTO;
    }


}
