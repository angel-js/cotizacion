package com.saavedraconstructora.cotizacion.service;

import java.util.List;
import com.saavedraconstructora.cotizacion.model.Role;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.RoleRepository;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> RolefindAll() {
        return roleRepository.findAll();
    }

    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
