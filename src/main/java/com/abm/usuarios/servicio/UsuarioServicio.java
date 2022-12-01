package com.abm.usuarios.servicio;

import com.abm.usuarios.dto.AltaUsuarioDTO;
import com.abm.usuarios.entidad.Usuario;

public interface UsuarioServicio {

    public Usuario guardarUsuario(AltaUsuarioDTO usuarioDTO);

    public Usuario editarUsuario(Usuario usuario);

    public Boolean eliminarUsuario(Long id);

    public Usuario findUsuario(Long id);

}
