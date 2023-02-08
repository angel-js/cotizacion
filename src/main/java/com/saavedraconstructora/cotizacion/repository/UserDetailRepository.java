package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<Usuario, Integer> {
}
