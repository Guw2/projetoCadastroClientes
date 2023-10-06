package com.lorian.projetocadastrocliente.resources.exceptions;

import com.lorian.projetocadastrocliente.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> ResourceNotFoundException(
            ResourceNotFoundException e, HttpServletRequest request
    ){
        StandardError se = new StandardError();
        se.setTimestamp(Instant.now());
        se.setStatus(HttpStatus.NOT_FOUND.value());
        se.setError("Not Found");
        se.setMessage(e.getMessage());
        se.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(se);
    }
}
