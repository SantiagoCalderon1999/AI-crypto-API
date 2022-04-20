package com.cryptoai.javaapi.binanceconnection.rest;

public class CryptoWrongDateFormatException extends RuntimeException{

    public CryptoWrongDateFormatException(String message) {
        super(message);
    }

    public CryptoWrongDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoWrongDateFormatException(Throwable cause) {
        super(cause);
    }
}
