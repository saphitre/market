package com.example.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Parameter is empty")
public class EmptyParamException extends RuntimeException{
    public EmptyParamException (){
        super("Parameter is empty");
    }
}
