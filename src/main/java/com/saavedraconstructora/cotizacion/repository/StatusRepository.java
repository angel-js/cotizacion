package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Status;
import com.saavedraconstructora.cotizacion.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query("from Status s order by s.name")
    List<Status> findAllStatus();
}
