package com.jonatan.forohub.infra.errores;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String mensaje){
        super(mensaje);
    }
}
