package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.exception.ResourceNotFoundException;
import com.saavedraconstructora.cotizacion.model.Cotizacion;
import com.saavedraconstructora.cotizacion.model.Departamento;
import com.saavedraconstructora.cotizacion.model.Status;
import com.saavedraconstructora.cotizacion.repository.CotizacionRepository;
import com.saavedraconstructora.cotizacion.repository.DepartamentoRepository;
import com.saavedraconstructora.cotizacion.repository.StatusRepository;
import org.slf4j.Logger;
import org.springframework.data.domain.Sort;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {
    // Injection of dependencies
    private final CotizacionRepository cotizacionRepository;
    private final DepartamentoRepository departamentoRepository;
    private static final Logger log = LoggerFactory.getLogger(CotizacionService.class);
    private final StatusRepository statusRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository,
                             DepartamentoRepository departamentoRepository,
                             StatusRepository statusRepository) {
        this.cotizacionRepository = cotizacionRepository;
        this.departamentoRepository = departamentoRepository;
        this.statusRepository = statusRepository;
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
            c.setStatus(cotizacionDetails.getStatus());
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

    /* STATUS */
    public List<Status> findAllStatus() {
        log.info("Cotizacion Service: Buscar Status");
        return statusRepository.findAllStatus();
    }


    public List<Cotizacion> findByMotivoContaining(String consulta, String status) {
        if (consulta == null || consulta.isEmpty()) {
            List<Cotizacion> cotizacionOrdFechVacia = cotizacionRepository.findByMotivoContainingAndStatus(consulta, status);
            cotizacionOrdFechVacia.sort((c1, c2) -> c2.getFecha_cotizacion().compareTo(c1.getFecha_cotizacion()));
            return cotizacionOrdFechVacia;
        } else {
            List<Cotizacion> cotizacionOrdFech = cotizacionRepository.findByMotivoContainingAndStatus(consulta, status);
            cotizacionOrdFech.sort((c1, c2) -> c2.getFecha_cotizacion().compareTo(c1.getFecha_cotizacion()));
            return cotizacionOrdFech;
        }
    }

    public List<Cotizacion> findByConsultaContaining(String consulta) {
        if (consulta == null || consulta.isEmpty()) {
            List<Cotizacion> cotizacionOrdFechVacia = cotizacionRepository.findByMotivoContaining(consulta);
            cotizacionOrdFechVacia.sort((c1, c2) -> c2.getFecha_cotizacion().compareTo(c1.getFecha_cotizacion()));
            return cotizacionOrdFechVacia;
        } else {
            List<Cotizacion> cotizacionOrdFech = cotizacionRepository.findByMotivoContaining(consulta);
            cotizacionOrdFech.sort((c1, c2) -> c2.getFecha_cotizacion().compareTo(c1.getFecha_cotizacion()));
            return cotizacionOrdFech;
        }
    }

    /** Método anteriormente ocupado
     * public List<Cotizacion> busqueda(String consulta) {
     *         log.info("Cotizacion Service: busqueda");
     *         return cotizacionRepository.findByMotivoContaining(consulta);
     *     }
     */
}
