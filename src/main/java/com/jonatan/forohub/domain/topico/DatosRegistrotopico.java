package com.jonatan.forohub.domain.topico;

import com.jonatan.forohub.domain.curso.Curso;
import com.jonatan.forohub.domain.curso.DatosCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistrotopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String autor,
        @NotNull DatosCurso curso
) {

    public DatosRegistrotopico(Topico topico){

        this(topico .getTitulo(), topico.getMensaje(), topico.getAutor(),new DatosCurso(topico.getCurso().getId()));
    }
}
