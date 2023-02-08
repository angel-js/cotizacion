package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    @Query("from Departamento dep order by dep.nombre")
    List<Departamento> buscarTodos();
}
