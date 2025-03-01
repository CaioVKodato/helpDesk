package com.caiokodato.helpdesk.controllers.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caiokodato.helpdesk.services.execptions.DataIntegrityViolationException;
import com.caiokodato.helpdesk.services.execptions.ObjectnotFoundExecption;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectnotFoundExecption.class)
    public ResponseEntity<StandartError> objectNotFoundException(ObjectnotFoundExecption ex, HttpServletRequest request ) {
        StandartError error = new StandartError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),"Object Not Found",ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandartError> DataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request ) {
        StandartError error = new StandartError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Violação de dados",ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request ) {
       
        ValidationError errors = new ValidationError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Validation error",
        "Erro na validação dos campos",request.getRequestURI());

        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            errors.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
