package com.abm.usuarios.transformador;

import com.abm.usuarios.dto.CiudadDTO;
import com.abm.usuarios.dto.CiudadYtemperaturaDTO;
import com.abm.usuarios.entidad.Ciudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CiudadTransformado {

    //    En vez de usar un ModelMapper (mapeador) voy a hacer el mapeo de Entidad a DTO de forma manual para las clases Ciudad y Weather.

    @Autowired
    private WeatherTransformado weatherTransformado;

    public CiudadYtemperaturaDTO ciudadYtemperaturaDTO(Ciudad ciudad) {
        CiudadYtemperaturaDTO ciudadYtemperaturaDTO = new CiudadYtemperaturaDTO();
        ciudadYtemperaturaDTO.setName(ciudad.getName());
        ciudadYtemperaturaDTO.setProvince(ciudad.getProvince());
        ciudadYtemperaturaDTO.setWeatherDTO(weatherTransformado.EntidadAdto(ciudad.getWeather()));
        return ciudadYtemperaturaDTO;
    }


    public List<CiudadYtemperaturaDTO> ciudadYtemperaturaDTO(List<Ciudad> ciudades) {
        return ciudades.stream()
                .map(ciudad -> ciudadYtemperaturaDTO(ciudad))
                .collect(Collectors.toList());
     }


    public CiudadDTO ciudadACiudadDTO(Ciudad ciudad) {
        CiudadDTO ciudadDTO = new CiudadDTO();
        ciudadDTO.setName(ciudad.getName());
        return ciudadDTO;
    }

    public List<CiudadDTO> ciudadACiudadDTO(List<Ciudad> ciudades) {
        return ciudades.stream()
                .map(ciudad -> ciudadACiudadDTO(ciudad))
                .collect(Collectors.toList());
    }

}
