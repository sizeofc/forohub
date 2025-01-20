package com.jonatan.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record DatosModificacionTopico(
        @NotNull
        @NumberFormat
        Long id,
        String titulo,
        String mensaje
) {
}
