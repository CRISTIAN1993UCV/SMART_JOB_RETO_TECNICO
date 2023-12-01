package com.smartjob.crud.service;

import org.springframework.http.ResponseEntity;

import com.smartjob.crud.model.entity.Usuario;


public interface UsuarioService {
    ResponseEntity<?> registerUsuario(Usuario userRequest);
}
