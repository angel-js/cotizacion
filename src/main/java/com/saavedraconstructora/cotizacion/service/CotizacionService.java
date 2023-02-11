package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.exception.ResourceNotFoundException;
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
    /*  READ  */
    public List<Cotizacion> buscar() {
        log.info("Cotizacion Service: buscar");
        return cotizacionRepository.buscarTodos();
    }

    public List<Departamento> buscarDepart() {
        log.info("Cotizacion Service: buscarDepart");
        return departamentoRepository.buscarTodos();
    }

    public Optional<Cotizacion> findById(Integer id) {
        log.info("Cotizacion Service: findById");
        return cotizacionRepository.findById(id);
    }

    public List<Cotizacion> busqueda(String consulta) {
        log.info("Cotizacion Service: busqueda");
        return cotizacionRepository.findByMotivoContaining(consulta);
    }

    /*  SAVE  */
    public Cotizacion guardar(Cotizacion cotizacion) {
        log.info("Cotizacion Service: guardar");
        return cotizacionRepository.save(cotizacion);
    }

    /*  UPDATE  */
    public void update(Integer id, Cotizacion cotizacionDetails) {
        log.info("Cotizacion Service: update");
        Optional<Cotizacion> cotizacion = findById(id);
        if (cotizacion.isPresent()) {
            Cotizacion c = cotizacion.get();
            c.setMotivo(cotizacionDetails.getMotivo());
            c.setDescripcion(cotizacionDetails.getDescripcion());
            c.setMonto(cotizacionDetails.getMonto());
            c.setDepartamento(cotizacionDetails.getDepartamento());
            log.info("The object is ready to inyect and is not empty");
            cotizacionRepository.save(c);
        } else {
            throw new ResourceNotFoundException("Cotizacion Not FOUND", "C", cotizacion);
        }
    }

    /* DELETE */
    public void deleteCotizacion(Integer id) {
        log.info("Supervisor Service: deleteSupervisor");
        cotizacionRepository.deleteById(id);
    }
}
