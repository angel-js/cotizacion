package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
