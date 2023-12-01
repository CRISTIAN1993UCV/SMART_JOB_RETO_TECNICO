package com.smartjob.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smartjob.crud.dao.UsuarioRepository;
import com.smartjob.crud.model.Phone;
import com.smartjob.crud.model.Usuario;
import com.smartjob.crud.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ResponseEntity<?> registerUsuario(Usuario userRequest) {
        if (!isValidEmail(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"mensaje\": \"El correo tiene un formato incorrecto\"}");
        }

        if (!isValidPassword(userRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"mensaje\": \"La contraseña tiene un formato incorrecto\"}");
        }

        if (usuarioRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"mensaje\": \"El correo ya registrado\"}");
        }

        Usuario user = createUsuarioFromRequest(userRequest);

        usuarioRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    private Usuario createUsuarioFromRequest(Usuario userRequest) {
        Usuario user = new Usuario();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhones(userRequest.getPhones().stream().map(p->createPhone(p)).collect(Collectors.toList()));
//        user.setId(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(generateToken());
        user.setActive(true);

        return user;
    }
    private Phone createPhone(Phone phone){
        Phone newPhone=new Phone();
        newPhone.setCityCode(phone.getCityCode());
        newPhone.setNumber(phone.getNumber());
        newPhone.setCountryCode(phone.getCountryCode());
        return newPhone;
    }
    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
