package com.abm.usuarios.util;

import com.abm.usuarios.dto.AltaUsuarioDTO;
import com.abm.usuarios.entidad.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversorDTOaEntidad {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario convertirUsuarioAaltaUsuarioDTO(AltaUsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }
}
