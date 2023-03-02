package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Supervisor;
import com.saavedraconstructora.cotizacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByEmail(String email);

    List<Usuario> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String name, String lastname);

    /**
    @Query("from Usuario us order by us.id")
    List<Usuario> findAll();

    Usuario findByUsername(String username);

    Usuario findByUsernameAndPassword(String username, String passwrod);**/
}
