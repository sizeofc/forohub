package com.jonatan.forohub.controller;

import com.jonatan.forohub.domain.curso.Curso;
import com.jonatan.forohub.domain.curso.CursoRepository;
import com.jonatan.forohub.domain.curso.DatosRegistroCurso;
import com.jonatan.forohub.domain.curso.DatosRespuestaCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    CursoRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                                              UriComponentsBuilder uriComponentsBuilder){
    Curso curso=repository.save(new Curso(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);

    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaCurso>> listarCursos(Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion)
                .map(DatosRespuestaCurso::new));
    }

}
