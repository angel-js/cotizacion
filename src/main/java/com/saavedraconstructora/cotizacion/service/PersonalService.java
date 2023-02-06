package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.domain.Supervisor;
import com.saavedraconstructora.cotizacion.repository.GerenteRepository;
import com.saavedraconstructora.cotizacion.repository.SupervisorRepository;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    private final GerenteRepository gerenteRepository;
    private final SupervisorRepository supervisorRepository;

    public PersonalService(GerenteRepository gerenteRepository, SupervisorRepository supervisorRepository) {
        this.gerenteRepository = gerenteRepository;
        this.supervisorRepository = supervisorRepository;
    }
    public List<Supervisor> busqueda(String consulta) {
        return supervisorRepository.findByNombreOrApellidoContaining(consulta, consulta);
    }
    public List<Supervisor> buscar() {
        return supervisorRepository.buscarTodos();
    }

    public Supervisor guardar(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }
}
