package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrabajoRepository extends JpaRepository<Trabajo, Integer> {

    //Delete
    void deleteById(Integer id); // Delete Supervisor

    // Listar por Usuario ID y Status
    @Query("from Trabajo t where t.status.id = :statusId and t.usuario.id = :usuarioId ORDER BY t.fecha_trabajo DESC")
    List<Trabajo> findByStatusAndUsuario(@Param("statusId") Integer statusId,
                                         @Param("usuarioId") Integer usuarioId);

    // Listar por Status todos los usuarios
    @Query("from Trabajo t where t.status.id = :statusId ORDER BY t.fecha_trabajo DESC")
    List<Trabajo> findByStatus(@Param("statusId") Integer statusId);


}
