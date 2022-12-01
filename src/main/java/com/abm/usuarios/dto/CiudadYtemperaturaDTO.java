package com.abm.usuarios.dto;

import lombok.Data;

@Data
public class CiudadYtemperaturaDTO {

    private String name;
    private String province;
    private WeatherDTO weatherDTO;
}
