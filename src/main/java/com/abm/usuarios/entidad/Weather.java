package com.abm.usuarios.entidad;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "WEATHER")
public class Weather {

    @Id
    private Long id;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "st")
    private Double st;

    @Column(name = "visibility")
    private Integer visibility;

    @Column(name = "wind_speed")
    private Integer wind_speed;

    @Column(name = "description")
    private String description;

    @Column(name = "temp")
    private Integer temp;

    @Column(name = "wing_deg")
    private String wing_deg;

    @Column(name = "tempDesc")
    private String tempDesc;
}
