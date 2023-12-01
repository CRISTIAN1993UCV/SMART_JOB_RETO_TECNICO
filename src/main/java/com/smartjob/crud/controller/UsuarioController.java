package com.smartjob.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartjob.crud.model.Usuario;
import com.smartjob.crud.service.UsuarioService;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario userRequest) {
        return userService.registerUsuario(userRequest);
    }
}
