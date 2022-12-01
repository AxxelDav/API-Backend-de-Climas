package com.abm.usuarios.entidad;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ROLES")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;
}
