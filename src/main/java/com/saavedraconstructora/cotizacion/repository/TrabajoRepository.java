package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajoRepository extends JpaRepository<Trabajo, Integer> {

    //Delete
    void deleteById(Integer id); // Delete Supervisor

}
