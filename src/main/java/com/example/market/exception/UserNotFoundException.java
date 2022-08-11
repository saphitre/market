package com.example.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found in project database")
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User with such username not found");
    }
}
