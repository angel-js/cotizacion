package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
