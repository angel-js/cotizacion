package com.saavedraconstructora.cotizacion.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // De esta forma indicamos que la lógica del ID es AUTO INCREMENTAL
    private Integer id;
    private String motivo;
    private String descripcion;
    private int monto;
    private LocalDate fecha_cotizacion;

    public Cotizacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public LocalDate getFecha_cotizacion() {
        return fecha_cotizacion;
    }

    public void setFecha_cotizacion(LocalDate fecha) {
        this.fecha_cotizacion = fecha;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                "id=" + id +
                ", motivo='" + motivo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                ", fecha=" + fecha_cotizacion +
                '}';
    }
}
