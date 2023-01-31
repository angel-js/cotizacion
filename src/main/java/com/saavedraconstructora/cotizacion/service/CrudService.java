package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.domain.Departamento;
import com.saavedraconstructora.cotizacion.repository.CotizacionRepository;
import com.saavedraconstructora.cotizacion.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {
    private final CotizacionRepository cotizacionRepository;
    private final DepartamentoRepository departamentoRepository;

    public CrudService(CotizacionRepository cotizacionRepository,
                       DepartamentoRepository departamentoRepository) {
        this.cotizacionRepository = cotizacionRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public List<Cotizacion> buscar() {
        return cotizacionRepository.buscarTodos();
    }

    public List<Departamento> buscarDepart() {
        return departamentoRepository.buscarTodos();
    }

    public Cotizacion guardar(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }
}
