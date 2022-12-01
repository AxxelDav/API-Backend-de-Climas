package com.abm.usuarios.enums;

public enum EnumsMensajesExcepcion {
    USUARIO_INCORRECTO("Usuario o Password ingresado es Incorrecto."),
    USUARIO_NO_ENCONTRADO("Usuario NO encontrado.");

    private final String mensaje;

    EnumsMensajesExcepcion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }

}
