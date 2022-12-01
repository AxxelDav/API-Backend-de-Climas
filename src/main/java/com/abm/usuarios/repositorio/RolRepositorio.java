package com.abm.usuarios.repositorio;

import com.abm.usuarios.entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombre);
}
