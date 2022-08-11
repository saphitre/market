package com.example.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "User already exists")
public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException(){
        super("User already exists");
    }
}
