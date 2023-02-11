package com.saavedraconstructora.cotizacion.dto;

import com.saavedraconstructora.cotizacion.model.Authority;
import com.saavedraconstructora.cotizacion.model.Role;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UsuarioDto {

    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    private String password2;
    @NotNull
    @NotEmpty
    private Role role;
    private Set<Authority> authorities;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
