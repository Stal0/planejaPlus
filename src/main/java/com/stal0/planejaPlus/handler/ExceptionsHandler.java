package com.stal0.planejaPlus.handler;

import com.stal0.planejaPlus.services.exceptions.DataBaseException;
import com.stal0.planejaPlus.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorMessageHandler> resourceNotFoundHandler(ResourceNotFoundException exception) {
        CustomErrorMessageHandler messageHandler = new CustomErrorMessageHandler(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageHandler);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomErrorMessageHandler> resourceNotFoundHandler(DataBaseException exception) {
        CustomErrorMessageHandler messageHandler = new CustomErrorMessageHandler(LocalDateTime.now(), HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageHandler);
    }
}
