package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findByName(String Name);

}
