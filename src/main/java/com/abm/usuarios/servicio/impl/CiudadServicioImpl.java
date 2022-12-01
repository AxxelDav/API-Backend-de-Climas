package com.abm.usuarios.servicio.impl;

import com.abm.usuarios.entidad.Ciudad;
import com.abm.usuarios.repositorio.CiudadRepositorio;
import com.abm.usuarios.repositorio.UsuarioRepositorio;
import com.abm.usuarios.servicio.CiudadServicio;
import com.abm.usuarios.servicio.impl.UsuarioServicioImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CiudadServicioImpl implements CiudadServicio {


    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.external.service.base-url}")
    private String basePath;

    private static Logger logger = LoggerFactory.getLogger(UsuarioServicioImpl.class);

/*
    //METODO ANTIGUO
    public void guardarObjeto(Api[] objeto){
        for (Api api : objeto)
            repositorioObjeto.save(api);
    }
*/


    public List<Ciudad> getApi(){
        Ciudad[] response = restTemplate.getForObject(basePath, Ciudad[].class);
        return Arrays.asList(response);
    }

    public Ciudad getOneApi(){
        Ciudad[] response = restTemplate.getForObject(basePath, Ciudad[].class);
        return response[0];
    }

    public Ciudad getoneApiFromRepo(String id){
        return ciudadRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto API no encontrado!!"));
    }

    public void saveApi(Ciudad ciudad){
        restTemplate.postForObject(basePath, ciudad, Ciudad.class);
    }

    public void saveApiOneAll(){
        Ciudad[] response = restTemplate.getForObject(basePath, Ciudad[].class);
        for(Ciudad elemento : response)
            ciudadRepositorio.save(elemento);
        logger.info("TODOS LOS ELEMENTOS FUERON GUARDADOS.");
    }

    public void saveApiOne(){
        Ciudad[] response = restTemplate.getForObject(basePath, Ciudad[].class);
        ciudadRepositorio.save(response[0]);
        logger.info("El primer elemento fue guardado con Exito!.");
    }

    public List<Ciudad> obtenerCiudades(){
        List<Ciudad> ciudades = ciudadRepositorio.findAll();
        return ciudades;
    }
}
