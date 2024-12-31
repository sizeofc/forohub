package com.jonatan.forohub.controller;

import com.jonatan.forohub.domain.topico.DatosRegistrotopico;
import com.jonatan.forohub.domain.topico.DatosRespuestaTopico;
import com.jonatan.forohub.domain.topico.Topico;
import com.jonatan.forohub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topico")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<DatosRegistrotopico> registrarTopico(@RequestBody @Valid DatosRegistrotopico datos,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = repository.save(new Topico(datos));
        DatosRegistrotopico datosRegistrotopico = new DatosRegistrotopico(topico);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRegistrotopico);
    }


    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listarTopicos(){
       var listaTopicos=repository.findAll();
       List<DatosRespuestaTopico> respuesta= listaTopicos.stream()
               .map(t->new DatosRespuestaTopico(t)).collect(Collectors.toList());
       return ResponseEntity.ok().body(respuesta);
    }
}
