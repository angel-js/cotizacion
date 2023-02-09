package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
