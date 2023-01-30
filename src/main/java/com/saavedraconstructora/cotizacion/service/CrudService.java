package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.repository.CotizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {
    private final CotizacionRepository cotizacionRepository;

    public CrudService(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    public List<Cotizacion> buscar() {
        return cotizacionRepository.buscarTodos();
    }
}
