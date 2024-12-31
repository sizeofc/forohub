package com.jonatan.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fechaCreacion
) {
    public DatosRespuestaTopico(Topico t) {
        this(t.getTitulo(), t.getMensaje(), t.getAutor(), t.getCurso(), t.getFechaCreacion());
    }
}
