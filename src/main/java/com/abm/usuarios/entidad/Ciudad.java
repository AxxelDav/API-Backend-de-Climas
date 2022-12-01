package com.abm.usuarios.entidad;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "API")
public class Ciudad {

    @Id
    private String _id;

    @Column(name = "dist")
    private Double dist;

    @Column(name = "lid")
    private Integer lid;

    @Column(name = "fid")
    private Integer fid;

    @Column(name = "int_number")
    private Integer int_number;

    @Column(name = "name")
    private String name;

    @Column(name = "province")
    private String province;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "zoom")
    private Integer zoom;

    @Column(name = "updated")
    private Long updated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_weather")
    private Weather weather;

    @Column(name = "forecast")
    private String forecast;

}
