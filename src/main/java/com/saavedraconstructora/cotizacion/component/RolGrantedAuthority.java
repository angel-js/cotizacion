package com.saavedraconstructora.cotizacion.component;

import org.springframework.security.core.GrantedAuthority;

public class RolGrantedAuthority implements GrantedAuthority {

    private final String rol;

    public RolGrantedAuthority(String rol) {
        this.rol = rol;
    }
    @Override
    public String getAuthority() {
        return this.rol;
    }
}