package com.abm.usuarios.transformador;

import com.abm.usuarios.dto.WeatherDTO;
import com.abm.usuarios.entidad.Weather;
import org.springframework.stereotype.Component;

@Component
public class WeatherTransformado {

    //    En vez de usar un ModelMapper (mapeador) voy a hacer el mapeo de Entidad a DTO de forma manual para las clases Ciudad y Weather.

    public WeatherDTO EntidadAdto(Weather weather) {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setHumidity(weather.getHumidity());
        weatherDTO.setDescription(weather.getDescription());
        weatherDTO.setWing_deg(weather.getWing_deg());
        weatherDTO.setTempDesc(weather.getTempDesc());

        return weatherDTO;
    }
}
