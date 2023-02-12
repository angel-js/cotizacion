package com.saavedraconstructora.cotizacion.repository;

import com.saavedraconstructora.cotizacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByEmail(String email);


    /**
    @Query("from Usuario us order by us.id")
    List<Usuario> findAll();

    Usuario findByUsername(String username);

    Usuario findByUsernameAndPassword(String username, String passwrod);**/
}
