package com.smartjob.crud.service;

import org.springframework.http.ResponseEntity;

import com.smartjob.crud.model.Usuario;

public interface UsuarioService {
    ResponseEntity<?> registerUsuario(Usuario userRequest);
}
