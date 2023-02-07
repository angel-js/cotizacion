package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.domain.Supervisor;
import com.saavedraconstructora.cotizacion.exception.ResourceNotFoundException;
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

    public Supervisor findById(Integer id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id ", "id", id));
    }

    // Update a Supervisor
    public void update(Integer id, Supervisor supervisorDetails) {
        System.out.println("UPDATE method of supervisor");
        Supervisor supervisor = findById(id);
        supervisor.setNombre(supervisorDetails.getNombre());
        supervisor.setApellido(supervisorDetails.getApellido());
        supervisor.setCorreo(supervisorDetails.getCorreo());
        supervisor.setTelefono(supervisorDetails.getTelefono());
        supervisor.setDepartamentos(supervisorDetails.getDepartamentos());
        System.out.println("Data of supervisor " + supervisor);
        supervisorRepository.save(supervisor);
    }

    // Delete a Supervisor
    public void deleteSupervisor(Integer id) {
        supervisorRepository.deleteById(id);
    }
}
