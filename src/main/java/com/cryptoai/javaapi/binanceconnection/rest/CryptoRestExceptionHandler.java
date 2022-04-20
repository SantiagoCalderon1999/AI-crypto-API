package com.cryptoai.javaapi.binanceconnection.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CryptoRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CryptoErrorResponse> handleException(Exception exception){


        CryptoErrorResponse error = new CryptoErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );



        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
