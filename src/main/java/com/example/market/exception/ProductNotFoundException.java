package com.example.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This product was not found in project database")
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(){
        super("Product was not found");
    }
}
