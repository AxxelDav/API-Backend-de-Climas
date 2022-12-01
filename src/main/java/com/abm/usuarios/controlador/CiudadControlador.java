package com.abm.usuarios.controlador;

import com.abm.usuarios.dto.CiudadDTO;
import com.abm.usuarios.dto.CiudadYtemperaturaDTO;
import com.abm.usuarios.entidad.Ciudad;
import com.abm.usuarios.servicio.CiudadServicio;
import com.abm.usuarios.transformador.CiudadTransformado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciudad")
public class CiudadControlador {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private CiudadTransformado ciudadTransformado;

    private static Logger logger = LoggerFactory.getLogger(ABMControlador.class);

/*
//PRIMERA FORMA (sin mapear a una clase Entidad)
    @GetMapping("/test")
    public Object getApi(){
        String url = "https://ws.smn.gob.ar/map_items/weather";
        Object forObject = restTemplate.getForObject(url, Object.class);
        logger.info("Result " + forObject);
        return forObject;
    }

//OTRA FORMA DE HACER EL PostMapping
    @PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Api> postApi(){
        String url = "https://ws.smn.gob.ar/map_items/weather";
        ResponseEntity<Api[]> response = restTemplate.getForEntity(url, Api[].class);
        Api[] apis = response.getBody();
        servicioUsuario.guardarObjeto(result);
        return Arrays.asList(result);
    }
*/

    //******************* Sin Base de Datos *******************//
    @GetMapping("/traer/todos") //Me traigo todos los datos (SIN Base de Datos), solamente con el RestTemplate
    public ResponseEntity<List<Ciudad>> getAll() {
        return new ResponseEntity<>(ciudadServicio.getApi(), HttpStatus.OK);
    }

    @GetMapping("/traerUno") //Me traigo solamente un dato (SIN Base de Datos), solamente con el RestTemplate
    public ResponseEntity<Ciudad> getOne() {
        return new ResponseEntity<>(ciudadServicio.getOneApi(), HttpStatus.OK);
    }

    @PostMapping("/guardarTodos") //Hago un POST, insertando todos los datos de la API externa (SIN Base de Datos)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveApi(@RequestBody Ciudad ciudad){
        ciudadServicio.saveApi(ciudad);
    }


    //******************* Con Base de Datos *******************//
    @GetMapping("/traerUno/delRepo/{id}") //Sirve para traerme un dato de la Base de Datos
    public ResponseEntity<Ciudad> getoneApiFromRepo(@PathVariable("id") String id) {
        return new ResponseEntity<>(ciudadServicio.getoneApiFromRepo(id), HttpStatus.OK);
    }

    @GetMapping("/guardar/todos") //Sirve para persistir todos los datos a la Base de Datos
    @ResponseStatus(HttpStatus.CREATED)
    public void saveApiOneAll(){
        ciudadServicio.saveApiOneAll();
    }

    @GetMapping("/guardar/uno") //Sirve para persistir solamente un dato a la Base de Datos
    @ResponseStatus(HttpStatus.CREATED)
    public void saveApiOne(){
        ciudadServicio.saveApiOne();
    }


    //****************** Consultando Ciudades y Temperatura segun el Rol del usuario ************************//

    @PreAuthorize("hasRole('ROLE_CONSULTA')")
    @GetMapping("/detalle/ciudades")
    public ResponseEntity<List<CiudadDTO>> detalleCiudades() {
        List<Ciudad> ciudades = ciudadServicio.obtenerCiudades();
        return new ResponseEntity<>(ciudadTransformado.ciudadACiudadDTO(ciudades), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/detalle/ciudadesYtemp")
    public ResponseEntity<List<CiudadYtemperaturaDTO>> detalleCiudadesYtemperatura() {
        List<Ciudad> ciudades = ciudadServicio.obtenerCiudades();
        return new ResponseEntity<>(ciudadTransformado.ciudadYtemperaturaDTO(ciudades), HttpStatus.OK);
    }
}
