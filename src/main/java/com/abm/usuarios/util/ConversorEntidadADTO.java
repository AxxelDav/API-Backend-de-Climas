package com.abm.usuarios.util;

import com.abm.usuarios.dto.AltaUsuarioDTO;
import com.abm.usuarios.dto.UsuarioDTO;
import com.abm.usuarios.entidad.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversorEntidadADTO {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO convertirUsuarioAdto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public AltaUsuarioDTO convertirUsuarioAaltaUsuarioDTO(Usuario usuario) {
        return modelMapper.map(usuario, AltaUsuarioDTO.class);
    }
}
