package com.enviro.assessment.grad001.mtramothole.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        logger.error("Error occurred", e);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred while processing the request.");
    }

    @ExceptionHandler(WasteNotFoundException.class)
    public ResponseEntity<Void> handleWasteNotFoundException(WasteNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.notFound().build();
    }
}