package com.choice.restservice.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.choice.wsdl.ServiceStatus;

@ControllerAdvice
public class HotelControllerErrorHandler {
    @ExceptionHandler(value = {ResponseStatusException.class})
    protected ResponseEntity<ServiceStatus> responseStatusExceptionHandler(ResponseStatusException exception) {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode(exception.getStatus().toString());
        serviceStatus.setMessage(Objects.requireNonNull(exception.getReason()));
        return ResponseEntity.status(exception.getStatus()).contentType(MediaType.APPLICATION_JSON).body(serviceStatus);
        
    }
}

