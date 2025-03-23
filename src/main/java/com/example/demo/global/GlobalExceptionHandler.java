package com.example.demo.global;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AwsServiceException.class)
    public ResponseEntity<String> handleServiceValidationException(AwsServiceException ex) {
        return new ResponseEntity<String>("AWS SERVICE EXCEPTION", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SdkClientException.class)
    public ResponseEntity<String> handleServiceValidationException(SdkClientException ex) {
        System.out.println(ex.getMessage() + "MESSAGE");
        return new ResponseEntity<String>("SDK CLIENT EXCEPTION", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleServiceValidationException(IOException ex) {
        return new ResponseEntity<String>("IO EXCEPTION", HttpStatus.BAD_REQUEST);
    }

}

