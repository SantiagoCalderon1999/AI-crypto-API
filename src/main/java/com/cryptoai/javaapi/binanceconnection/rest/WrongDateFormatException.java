package com.cryptoai.javaapi.binanceconnection.rest;

public class WrongDateFormatException extends RuntimeException{

    public WrongDateFormatException(String message) {
        super(message);
    }

    public WrongDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDateFormatException(Throwable cause) {
        super(cause);
    }
}
