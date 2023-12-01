package com.smartjob.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.smartjob.crud.config.RegexProperties;
import com.smartjob.crud.dao.UsuarioRepository;
import com.smartjob.crud.model.dto.MessageResponseDto;
import com.smartjob.crud.model.entity.Phone;
import com.smartjob.crud.model.entity.Usuario;
import com.smartjob.crud.security.JwtUtil;
import com.smartjob.crud.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RegexProperties regexProperties;

    public ResponseEntity<?> registerUsuario(Usuario userRequest) {
        if (!isValidEmail(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(MessageResponseDto.builder().mensaje("El correo tiene un formato incorrecto").build());
        }

        if (!isValidPassword(userRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(MessageResponseDto.builder().mensaje("La contraseña tiene un formato incorrecto").build());
        }

        if (usuarioRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(MessageResponseDto.builder().mensaje("El correo ya registrado").build());
        }

        Usuario user = buildUsuarioFromRequest(userRequest);

        usuarioRepository.save(user);

        String token = jwtUtil.generateToken(new User(user.getEmail(), user.getPassword(), new ArrayList<>()));
        user.setToken(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    private Usuario buildUsuarioFromRequest(Usuario userRequest) {
        Usuario user = new Usuario();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhones(userRequest.getPhones().stream().map(p->buildPhone(p)).collect(Collectors.toList()));
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);

        return user;
    }
    private Phone buildPhone(Phone phone){
        Phone newPhone=new Phone();
        newPhone.setCityCode(phone.getCityCode());
        newPhone.setNumber(phone.getNumber());
        newPhone.setCountryCode(phone.getCountryCode());
        return newPhone;
    }
 
    private boolean isValidEmail(String correo) {
       return correo.matches(regexProperties.getEmailRegex());
    }


    private boolean isValidPassword(String password) {
       return password.matches(regexProperties.getPasswordRegex());
    }

}
