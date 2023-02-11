package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {

    @Query("from Cotizacion c order by c.fecha_cotizacion")
    List<Cotizacion> buscarTodos();

    @Query("from Cotizacion c")
    List<Cotizacion> findByMotivoContaining(@Param("consulta") String consulta);

}
