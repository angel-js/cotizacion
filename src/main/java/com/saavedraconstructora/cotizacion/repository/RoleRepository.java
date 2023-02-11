package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Role;
import com.saavedraconstructora.cotizacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    @Query("from Role r order by r.id")
    List<Role> findAll();
}
