package com.choice.restservice.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class HotelControllerErrorHandler {
    @ExceptionHandler(value = {ResponseStatusException.class})
    protected ResponseEntity<String> responseStatusExceptionHandler(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatus()).contentType(MediaType.APPLICATION_JSON).body(Objects.requireNonNull(exception.getReason()));
    }
}
