package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.exception.ResourceNotFoundException;
import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepository trabajoRepository;

    public Trabajo findById(Integer id) {
        return trabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trabajo not found with id ", "id", id));
    }

    public void deleteTrabajo(Integer id) {
        trabajoRepository.deleteById(id);
    }
}
