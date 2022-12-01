package com.abm.usuarios.servicio.impl;

import com.abm.usuarios.dto.AltaUsuarioDTO;
import com.abm.usuarios.entidad.Usuario;
import com.abm.usuarios.enums.EnumsMensajesExcepcion;
import com.abm.usuarios.excepcion.ExcepcionUsuarioNoEncontrado;
import com.abm.usuarios.repositorio.UsuarioRepositorio;
import com.abm.usuarios.servicio.UsuarioServicio;
import com.abm.usuarios.util.ConversorDTOaEntidad;
import com.abm.usuarios.util.ValidaUsuarioYpassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConversorDTOaEntidad conversor;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private static Logger logger = LoggerFactory.getLogger(UsuarioServicioImpl.class);

    public Usuario guardarUsuario(AltaUsuarioDTO usuarioDTO) {
        ValidaUsuarioYpassword.validaUsuarioYpassword(usuarioDTO);
        Usuario usuario = conversor.convertirUsuarioAaltaUsuarioDTO(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); //Codifico la password del DTO para que cuando haga el mapeo tambien quede encriptado al momento de guardarlo en la Base de Datos.
        logger.info("Contraseña codificada es  ->  " +  passwordEncoder.encode(usuarioDTO.getPassword()));
        return usuarioRepositorio.save(usuario);
    }

    public Usuario editarUsuario(Usuario usuario) {
        Usuario userAmodificar = usuarioRepositorio.findById(usuario.getId())
                                                        .orElseThrow(() -> new ExcepcionUsuarioNoEncontrado(EnumsMensajesExcepcion.USUARIO_NO_ENCONTRADO.getMensaje()));
        userAmodificar.setNombre(usuario.getNombre());
        userAmodificar.setPassword(usuario.getPassword());
        userAmodificar.setEmail(usuario.getEmail());

        return usuarioRepositorio.save(userAmodificar);
    }

    public Boolean eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new ExcepcionUsuarioNoEncontrado(EnumsMensajesExcepcion.USUARIO_NO_ENCONTRADO.getMensaje()));
        usuarioRepositorio.deleteById(usuario.getId());
        return true;
    }

//    public Boolean validarLogin(String nombreUsuario, String password) {
//        usuarioRepositorio.validarLogin(nombreUsuario, password)
//                            .orElseThrow(() -> new ExcepcionUsuarioIncorrecto(EnumsMensajesExcepcion.USUARIO_INCORRECTO.getMensaje()));
//        return true;
//    }

    public Usuario findUsuario(Long id){
        return usuarioRepositorio.findById(id).get();
    }




}
