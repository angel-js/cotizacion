package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.model.Rol;
import com.saavedraconstructora.cotizacion.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> mostrarRoles() {
        return rolRepository.findAll();
    }
}
