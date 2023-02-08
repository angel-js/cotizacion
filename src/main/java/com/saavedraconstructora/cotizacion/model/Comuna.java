package com.saavedraconstructora.cotizacion.model;

import javax.persistence.*;

@Entity
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    @ManyToOne
    @JoinColumn(unique = true)
    private Region region;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Region getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "Comuna{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", region=" + region +
                '}';
    }
}
