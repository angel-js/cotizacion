package com.saavedraconstructora.cotizacion.interfaces;

import com.saavedraconstructora.cotizacion.dto.UsuarioRegistroDto;
import com.saavedraconstructora.cotizacion.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServicio extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDto registroDto);
}
