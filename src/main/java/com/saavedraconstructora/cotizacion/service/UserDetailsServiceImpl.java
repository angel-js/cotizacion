package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.model.UsuarioExtendidoDetails;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EncoderService encoderService;


    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
        return new UsuarioExtendidoDetails(usuario);
    }

    public Usuario createUser(Usuario usuario) {
        usuario.setPassword(encoderService.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}