package com.saavedraconstructora.cotizacion.dto;

import com.saavedraconstructora.cotizacion.model.Departamento;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class SupervisorDto {
    @NotNull
    @NotEmpty
    @Size(min=2, max=40)
    private String nombre;
    @Size(min=2, max=50)
    private String apellido;
    @Size(min=2, max=13)
    private String telefono;
    @Email
    private String correo;
    @NotEmpty
    @NotNull
    @Size(min=2, max=30)
    private String cargo;
    @NotNull
    @NotEmpty
    private Set<Departamento> departamentos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Set<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
