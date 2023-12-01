package com.smartjob.crud.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.smartjob.crud.exception.UsuarioNoEncontradoException;
import com.smartjob.crud.exception.NombreUsuarioDuplicadoException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNoEncontradoException(UsuarioNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NombreUsuarioDuplicadoException.class)
    public ResponseEntity<String> handleNombreUsuarioDuplicadoException(NombreUsuarioDuplicadoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
