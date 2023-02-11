package com.saavedraconstructora.cotizacion.dto;

import com.saavedraconstructora.cotizacion.model.Cotizacion;
import com.saavedraconstructora.cotizacion.model.Departamento;
import com.saavedraconstructora.cotizacion.model.Status;
import com.sun.istack.NotNull;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
public class CotizacionDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min=2, max=50)
    private String motivo;
    @Size(min=2, max=400)
    private String descripcion;
    private int monto;
    private LocalDate fecha_cotizacion;
    @NotNull
    private Departamento departamento;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CotizacionDto() {
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
    public void setFecha_cotizacion(LocalDate fecha_cotizacion) {
        this.fecha_cotizacion = fecha_cotizacion;
    }
    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
