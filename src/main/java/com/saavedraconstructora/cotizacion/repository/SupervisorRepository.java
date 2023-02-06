package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.domain.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

    @Query("from Supervisor s order by s.nombre")
    List<Supervisor> buscarTodos();

    List<Supervisor> findByNombreOrApellidoContaining(String nombre, String apellido);
}
