package com.smartjob.crud.exception;

public class NombreUsuarioDuplicadoException extends RuntimeException {
    public NombreUsuarioDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
