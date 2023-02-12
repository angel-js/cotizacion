package com.saavedraconstructora.cotizacion.dto;

import com.saavedraconstructora.cotizacion.model.Rol;


public class UsuarioRegistroDto {
    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Rol rol;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioRegistroDto(Integer id, String name, String lastname, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistroDto(String name, String lastname, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistroDto(String email) {
        this.email = email;
    }

    public UsuarioRegistroDto() {
    }

    public UsuarioRegistroDto(String name, String lastname, String email, String password, Rol rol) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
}
