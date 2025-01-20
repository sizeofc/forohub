package com.jonatan.forohub.controller;

import com.jonatan.forohub.domain.curso.Curso;
import com.jonatan.forohub.domain.curso.CursoRepository;
import com.jonatan.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistrotopico topico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("curso----->" + topico.curso().id());
        Curso curso = cursoRepository.getReferenceById(topico.curso().id());

        if (curso == null) {
            throw new ValidationException("EL id del curso es invalido");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Topico newtopico = new Topico(topico, curso,currentUsername);
        newtopico.setAutor(currentUsername);



        newtopico = repository.save(new Topico(newtopico));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(newtopico);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(newtopico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }


    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listarTopicos() {
        var listaTopicos = repository.findAll();
        List<DatosRespuestaTopico> respuesta = listaTopicos.stream()
                .map(t -> new DatosRespuestaTopico(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(respuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity modificarTopico(@RequestBody @Valid DatosModificacionTopico datos) {
        Topico topico = topicoService.updateTopic(datos.id(),datos);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable @Valid Long id){
        topicoService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTopicoPorId(@PathVariable @Valid Long id){
        Topico topico= repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DatosRespuestaTopico(topico));
    }
}
