package com.foodrecipe.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus request = HttpStatus.BAD_REQUEST;
        ApiException exception =  ApiException.builder()
                .message(e.getMessage())
                .httpStatus(request)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(exception, request);
    }
}
