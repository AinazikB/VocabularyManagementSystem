package com.example.vocabularymanagementsystem.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException exception) {
        String message = "Resource not found: " + exception.getMessage();
        System.out.println("Not Found Exception Handler: " + message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
