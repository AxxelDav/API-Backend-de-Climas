package com.abm.usuarios.entidad;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "USUARIOS", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}), @UniqueConstraint(columnNames = {"email"})}) //En esta tabla, solamente nombreUsuario y Email son unicos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    protected String password;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
                name = "usuarios_roles",
                joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
               )
    private Set<Rol> roles = new HashSet<>();

}
