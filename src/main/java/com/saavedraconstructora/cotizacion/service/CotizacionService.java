package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.model.Cotizacion;
import com.saavedraconstructora.cotizacion.model.Departamento;
import com.saavedraconstructora.cotizacion.repository.CotizacionRepository;
import com.saavedraconstructora.cotizacion.repository.DepartamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {
    // Injection of dependencies
    private final CotizacionRepository cotizacionRepository;
    private final DepartamentoRepository departamentoRepository;
    private static final Logger log = LoggerFactory.getLogger(CotizacionService.class);
    public CotizacionService(CotizacionRepository cotizacionRepository,
                             DepartamentoRepository departamentoRepository) {
        this.cotizacionRepository = cotizacionRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public List<Cotizacion> buscar() {
        log.info("Cotizacion Service: buscar");
        return cotizacionRepository.buscarTodos();
    }

    public List<Departamento> buscarDepart() {
        log.info("Cotizacion Service: buscarDepart");
        return departamentoRepository.buscarTodos();
    }

    public Cotizacion guardar(Cotizacion cotizacion) {
        log.info("Cotizacion Service: guardar");
        return cotizacionRepository.save(cotizacion);
    }

    public Optional<Cotizacion> findById(Integer id) {
        log.info("Cotizacion Service: findById");
        return cotizacionRepository.findById(id);
    }

    public List<Cotizacion> busqueda(String consulta) {
        log.info("Cotizacion Service: busqueda");
        return cotizacionRepository.findByMotivoContaining(consulta);
    }
}
