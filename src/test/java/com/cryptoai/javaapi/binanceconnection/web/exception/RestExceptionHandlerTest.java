package com.cryptoai.javaapi.binanceconnection.web.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestExceptionHandlerTest {


    @Test
    void handleWrongDateFormatException() {

        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        Exception exception = new Exception("Wrong date format");

        ResponseEntity<ErrorResponse> httpsResponse = restExceptionHandler.handleWrongDateFormatException(exception);

        assertNotNull(httpsResponse);
        assertThat(httpsResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void handleNonExistentZipFileException() {

        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

        Exception exception = new Exception("The file does not exist");

        ResponseEntity<ErrorResponse> httpsResponse = restExceptionHandler.handleNonexistentZipFileException(exception);

        assertNotNull(httpsResponse);
        assertThat(httpsResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}