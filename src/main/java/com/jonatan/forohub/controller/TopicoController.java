package com.jonatan.forohub.controller;

import com.jonatan.forohub.domain.curso.Curso;
import com.jonatan.forohub.domain.curso.CursoRepository;
import com.jonatan.forohub.domain.topico.DatosRegistrotopico;
import com.jonatan.forohub.domain.topico.DatosRespuestaTopico;
import com.jonatan.forohub.domain.topico.Topico;
import com.jonatan.forohub.domain.topico.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistrotopico topico,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("curso----->"+topico.curso().id());
        Curso curso=  cursoRepository.getReferenceById(topico.curso().id());

        if(curso==null){
            throw new ValidationException("EL id del curso es invalido");
        }
        Topico newtopico = new Topico(topico,curso);
        System.out.println("nuevo topico creado: "+newtopico.getTitulo()+newtopico.getCurso().getNombre());

         newtopico= repository.save(new Topico(newtopico));

        System.out.println(newtopico.toString());
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(newtopico);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(newtopico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }


    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listarTopicos(){
       var listaTopicos=repository.findAll();
       List<DatosRespuestaTopico> respuesta= listaTopicos.stream()
               .map(t->new DatosRespuestaTopico(t)).collect(Collectors.toList());
       return ResponseEntity.ok().body(respuesta);
    }
}
