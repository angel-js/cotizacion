package com.saavedraconstructora.cotizacion.service;

import java.util.List;

import com.saavedraconstructora.cotizacion.controller.HomeController;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario findByUsernameAndPassword(String username, String password) {
        log.info("------------------------ Password --->" + password);
        Usuario u =  usuarioRepository.findByUsername(username);
        if(u != null && isPasswordValid(password, u.getPassword())) {
            return u;
        } else {
            return null;
        }
    }

    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
