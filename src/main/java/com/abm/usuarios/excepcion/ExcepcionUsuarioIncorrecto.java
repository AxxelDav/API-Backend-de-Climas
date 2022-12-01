package com.abm.usuarios.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExcepcionUsuarioIncorrecto extends RuntimeException {

    public ExcepcionUsuarioIncorrecto(String mensaje) {
        super(mensaje);
    }
}
