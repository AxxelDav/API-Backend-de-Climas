package com.abm.usuarios.util;

import com.abm.usuarios.dto.AltaUsuarioDTO;
import com.abm.usuarios.enums.EnumsMensajesExcepcion;
import com.abm.usuarios.excepcion.ExcepcionUsuarioIncorrecto;

public class ValidaUsuarioYpassword {

    public static boolean validaUsuarioYpassword(AltaUsuarioDTO usuarioDTO) {
        if(usuarioDTO.getNombre() == null || usuarioDTO.getPassword() == null) {
            throw new ExcepcionUsuarioIncorrecto(EnumsMensajesExcepcion.USUARIO_INCORRECTO.getMensaje());
        }
        return true;
    }
}
