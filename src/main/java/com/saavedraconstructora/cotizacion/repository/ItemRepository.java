package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    // Buscar todos los items según trabajo Id
    @Query("from Item i where i.trabajo.id = :trbjId")
    List<Item> findByTrabajoId(@Param("trbjId") Integer id);


}
