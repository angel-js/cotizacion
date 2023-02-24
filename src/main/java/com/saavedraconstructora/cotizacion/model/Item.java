package com.saavedraconstructora.cotizacion.model;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private int monto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajo_id")
    private Trabajo trabajo;


    public Item(Integer id, String nombre, int monto, Trabajo trabajo) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.trabajo = trabajo;
    }

    public Item(String nombre, int monto, Trabajo trabajo) {
        this.nombre = nombre;
        this.monto = monto;
        this.trabajo = trabajo;
    }

    public Item() {
    }

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

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
}
