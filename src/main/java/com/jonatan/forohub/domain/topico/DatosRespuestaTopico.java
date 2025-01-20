package com.jonatan.forohub.domain.topico;

import com.jonatan.forohub.domain.curso.Curso;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        String autor,
        Long cursoId,
        LocalDateTime fechaCreacion
) {
    public DatosRespuestaTopico(Topico t) {
        this(t.getTitulo(), t.getMensaje(), t.getAutor(), t.getCurso().getId(), t.getFechaCreacion());
    }
}
