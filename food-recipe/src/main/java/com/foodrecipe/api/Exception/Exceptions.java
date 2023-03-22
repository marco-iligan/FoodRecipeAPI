package com.foodrecipe.api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class Exceptions extends RuntimeException{
    public Exceptions(){}

    public Exceptions(String message, Throwable cause){
        super(message);
    }
}
