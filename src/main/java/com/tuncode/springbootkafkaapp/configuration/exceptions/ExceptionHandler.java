package com.tuncode.springbootkafkaapp.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author caksuna on 15.05.2024 11:28
 */

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({SourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleSourceNotFoundException(SourceNotFoundException exc) {
        ErrorResponse errorResponse = new ErrorResponse(
                exc.getLocalizedMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
