package com.abm.usuarios.dto;

import lombok.Data;

@Data
public class AltaUsuarioDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String username;

    protected String password;

    private String email;
}
