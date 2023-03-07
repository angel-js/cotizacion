package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Departamento;
import com.saavedraconstructora.cotizacion.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    @Query("from Departamento dep order by dep.nombre")
    List<Departamento> buscarTodos();

    @Query("SELECT s FROM Supervisor s JOIN s.departamentos d WHERE d.id = :localId")
    List<Supervisor> findSupervisorsByLocalId(@Param("localId") Integer localId);
}
