package com.jonatan.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;


public record DatosRegistrotopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String autor,
        @NotBlank String curso
) {

    public DatosRegistrotopico(Topico topico){
        this(topico .getTitulo(), topico.getMensaje(), "prueba1","curso 1");
    }
}
