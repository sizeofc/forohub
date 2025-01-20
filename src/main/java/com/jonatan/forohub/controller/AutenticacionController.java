package com.jonatan.forohub.controller;

import com.jonatan.forohub.domain.usuario.DatosautenticacionUsuario;
import com.jonatan.forohub.domain.usuario.Usuario;
import com.jonatan.forohub.infra.security.DataJWTtoken;
import com.jonatan.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class AutenticacionController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosautenticacionUsuario datosautenticacionUsuario) {
        Authentication token = new UsernamePasswordAuthenticationToken(datosautenticacionUsuario.username(), datosautenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(token);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DataJWTtoken(JWTtoken));
    }
}
