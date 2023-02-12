package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean authenticate(String username, String password) {
        Usuario usuario = usuarioRepository.findByEmail(username);

        if (usuario != null && usuario.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
