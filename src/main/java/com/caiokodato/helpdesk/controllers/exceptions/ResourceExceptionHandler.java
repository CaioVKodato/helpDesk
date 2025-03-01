package com.caiokodato.helpdesk.controllers.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.caiokodato.helpdesk.services.execptions.DataIntegrityViolationException;
import com.caiokodato.helpdesk.services.execptions.ObjectnotFoundExecption;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    public ResponseEntity<StandartError> objectNotFoundException(ObjectnotFoundExecption ex, HttpServletRequest request ) {
        StandartError error = new StandartError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),"Object Not Found",ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    public ResponseEntity<StandartError> DataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request ) {
        StandartError error = new StandartError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Violação de dados",ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
