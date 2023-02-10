package com.saavedraconstructora.cotizacion.service;

import java.util.List;

import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
