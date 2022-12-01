package com.abm.usuarios.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExcepcionUsuarioNoEncontrado extends RuntimeException{

    public ExcepcionUsuarioNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
