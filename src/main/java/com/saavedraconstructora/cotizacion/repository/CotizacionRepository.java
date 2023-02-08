package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {

    @Query("from Cotizacion c order by c.motivo")
    List<Cotizacion> buscarTodos();

    List<Cotizacion> findByMotivoContaining(String consulta);
}
