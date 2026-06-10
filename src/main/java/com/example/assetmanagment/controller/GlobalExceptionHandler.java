package com.example.assetmanagment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
            //Return a custom error message with HTTP 500 status
            return new ResponseEntity<>("A runtime error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(Exception ex) {
            //Return a custom error message with HTTP 500 status
           return new ResponseEntity<>("An Illegal Argument error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InterruptedException.class)
   public ResponseEntity<String> handleInterruptedException(Exception ex) {
            //Return a custom error message with HTTP 500 status
            return new ResponseEntity<>("An Interrupted error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
   public ResponseEntity<String> handleNullPointerException(Exception ex) {
            //Return a custom error message with HTTP 500 status
            return new ResponseEntity<>("A Null Pointer Exception occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
            //Return a custom error message with HTTP 500 status
            return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
