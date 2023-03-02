package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.exception.ResourceNotFoundException;
import com.saavedraconstructora.cotizacion.model.Supervisor;
import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.TrabajoRepository;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolService {
    private static final Logger log = LoggerFactory.getLogger(SupervisorService.class);
    @Autowired
    private TrabajoRepository trabajoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Trabajo findById(Integer id) {
        return trabajoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id ", "id", id));
    }
    public List<Usuario> findByNames(String consulta) {
        log.info("Supervisor Service: busqueda");
        return usuarioRepository.findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(consulta, consulta);
    }
}
