package com.saavedraconstructora.cotizacion.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String direccion;
    @ManyToOne
    private Comuna comuna;
    @ManyToMany(mappedBy = "departamentos")
    private Set<Supervisor> supervisors;

    public Set<Supervisor> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(Set<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }

    public Set<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(Set<Gerente> gerentes) {
        this.gerentes = gerentes;
    }

    @ManyToMany(mappedBy = "departamentos")
    private Set<Gerente> gerentes;

    public Set<Supervisor> getEmpleados() {
        return supervisors;
    }

    public void setEmpleados(Set<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
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
}
