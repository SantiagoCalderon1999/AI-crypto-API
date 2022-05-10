package com.cryptoai.javaapi.binanceconnection.web.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class RestExceptionHandlerTest {


    @Test
    void handleWrongDateFormatException() {

        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        Exception exception = new Exception("Wrong date format");

        ResponseEntity<ErrorResponse> httpsResponse = restExceptionHandler.handleWrongDateFormatException(exception);

        assertNotNull(httpsResponse);
    }
}