package com.tuncode.springbootkafkaapp.configuration.exceptions;

/**
 * @author caksuna on 15.05.2024 11:29
 */
public class SourceNotFoundException extends RuntimeException {

    public SourceNotFoundException(String message) {
        super(message);
    }

    public SourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
