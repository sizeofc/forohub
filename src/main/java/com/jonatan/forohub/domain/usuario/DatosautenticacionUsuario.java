package com.jonatan.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosautenticacionUsuario(
        @NotBlank
        String username,
        @NotBlank
        String clave
) {
}
