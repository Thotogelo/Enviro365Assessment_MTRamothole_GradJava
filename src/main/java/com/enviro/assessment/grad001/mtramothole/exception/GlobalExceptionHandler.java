package com.enviro.assessment.grad001.mtramothole.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    // This method handles all types of exceptions.
    // It logs the error and returns a generic error message with a status of INTERNAL_SERVER_ERROR.\
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Error occurred", e);
        return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // This method specifically handles WasteNotFoundException.
    // It returns the exception's message with a status of NOT_FOUND.
    @ExceptionHandler(WasteNotFoundException.class)
    public ResponseEntity<String> handleWasteNotFoundException(WasteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}