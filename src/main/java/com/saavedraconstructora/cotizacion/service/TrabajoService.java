package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.exception.ResourceNotFoundException;
import com.saavedraconstructora.cotizacion.model.Departamento;
import com.saavedraconstructora.cotizacion.model.Status;
import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.DepartamentoRepository;
import com.saavedraconstructora.cotizacion.repository.StatusRepository;
import com.saavedraconstructora.cotizacion.repository.TrabajoRepository;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajoService {
    private static final Logger log = LoggerFactory.getLogger(CotizacionService.class);
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private TrabajoRepository trabajoRepository;

    @Autowired
    private StatusRepository statusRepository;

    public Trabajo findById(Integer id) {
        return trabajoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id ", "id", id));
    }

    public void deleteTrabajo(Integer id) {
        trabajoRepository.deleteById(id);
    }

    public List<Departamento> buscarDepart() {
        log.info("Cotizacion Service: buscarDepart");
        return departamentoRepository.buscarTodos();
    }

    //TODO create a method can search for mail and return usuario (OBJECT)

    // Devolver data USUARIO segun su EMAIL
    public Usuario buscarUsuarioXMail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /* STATUS */
    public List<Status> findAllStatus() {
        log.info("Cotizacion Service: Buscar Status");
        return statusRepository.findAllStatus();
    }
}
