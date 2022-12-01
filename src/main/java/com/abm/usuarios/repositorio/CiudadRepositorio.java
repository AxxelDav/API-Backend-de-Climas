package com.abm.usuarios.repositorio;

import com.abm.usuarios.entidad.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad, String> {
}
