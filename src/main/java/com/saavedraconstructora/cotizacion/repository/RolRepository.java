package com.saavedraconstructora.cotizacion.repository;

import java.util.List;
import com.saavedraconstructora.cotizacion.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    public List<Rol> findAll();
}
