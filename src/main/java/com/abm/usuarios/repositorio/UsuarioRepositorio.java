package com.abm.usuarios.repositorio;

import com.abm.usuarios.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

//    @Query(value = "SELECT * FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario and u.password = :password", nativeQuery = true)
//    public Optional<Usuario> validarLogin(@Param("nombreUsuario") String NombreUsuario, @Param("password") String password);

//********************** Spring Security **********************//
    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByUsernameOrEmail(String username, String email);

    public Optional<Usuario> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
