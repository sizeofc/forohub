package com.jonatan.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamientoErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosError::new);
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErrorAtributoEnBlanco(HttpMessageNotReadableException e){
        return ResponseEntity.badRequest().body("Todos los campos deben estar completos");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity tratarErrorModificacionNoAutorizada(UnauthorizedException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DatosError(String campo, String mensaje) {
        DatosError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());

        }
    }
}
