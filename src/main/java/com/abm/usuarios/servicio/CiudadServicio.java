package com.abm.usuarios.servicio;

import com.abm.usuarios.entidad.Ciudad;

import java.util.List;

public interface CiudadServicio {

    public List<Ciudad> getApi();

    public Ciudad getOneApi();

    public Ciudad getoneApiFromRepo(String id);

    public void saveApi(Ciudad ciudad);

    public void saveApiOneAll();

    public void saveApiOne();

    public List<Ciudad> obtenerCiudades();
}
