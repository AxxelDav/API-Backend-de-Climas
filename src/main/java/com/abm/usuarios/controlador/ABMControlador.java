package com.abm.usuarios.controlador;

import com.abm.usuarios.dto.AltaUsuarioDTO;
import com.abm.usuarios.dto.UsuarioDTO;
import com.abm.usuarios.entidad.Usuario;
import com.abm.usuarios.servicio.UsuarioServicio;
import com.abm.usuarios.util.ConversorEntidadADTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/abm/usuario")
public class ABMControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ConversorEntidadADTO conversor;

    @Autowired
    private RestTemplate restTemplate;

    private static Logger logger = LoggerFactory.getLogger(ABMControlador.class);

    //###############################    ABM   ############################### //

    @PostMapping("/alta")
    public ResponseEntity<UsuarioDTO> altaUsuario(@RequestBody AltaUsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario =  usuarioServicio.guardarUsuario(usuarioDTO);
        logger.info("Guardando Usuario Exitosamente");
        return new ResponseEntity<>(conversor.convertirUsuarioAdto(nuevoUsuario), HttpStatus.CREATED);
    }

    @PutMapping("/modificacion")
    public ResponseEntity<UsuarioDTO> modificacionUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUser = usuarioServicio.editarUsuario(usuario);
        return new ResponseEntity<>(conversor.convertirUsuarioAdto(nuevoUser), HttpStatus.OK);
    }

    @DeleteMapping("/baja/{id}")
    public ResponseEntity<Boolean> bajaUsuario(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioServicio.eliminarUsuario(id), HttpStatus.OK);
    }

    @GetMapping("/holis/{id}")
    public ResponseEntity<UsuarioDTO> findUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findUsuario(id);
        return new ResponseEntity<>(conversor.convertirUsuarioAdto(usuario), HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Object> loginUsuario(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
//        if(bindingResult.hasErrors())
//            return new ResponseEntity<>(new Mensaje("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
//        try {
//            servicioUsuario.validarLogin(loginUsuario.getNombreUsuario(), loginUsuario.getPassword());
//            return new ResponseEntity<>(new Mensaje("Logueo con exito."), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new Mensaje("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
//        }
//    }

}
