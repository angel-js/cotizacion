package com.saavedraconstructora.cotizacion.component;

import com.saavedraconstructora.cotizacion.model.Authority;
import com.saavedraconstructora.cotizacion.model.Role;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.AuthorityRepository;
import com.saavedraconstructora.cotizacion.repository.RoleRepository;
import com.saavedraconstructora.cotizacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    private final UsuarioService usuarioService;

    @Autowired
    public DataLoader(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Authority authorityCreate = new Authority();
        authorityCreate.setName("AUTHORITY_CREATE");

        Authority authorityRead = new Authority();
        authorityRead.setName("AUTHORITY_READ");

        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setUsername("admin");
        usuarioAdmin.setPassword(new BCryptPasswordEncoder().encode("12345"));
        usuarioAdmin.setRole(roleAdmin);
        usuarioAdmin.setAuthorities(Set.of(authorityCreate));

        Usuario usuarioUser = new Usuario();
        usuarioUser.setUsername("user");
        usuarioUser.setPassword(new BCryptPasswordEncoder().encode("12345"));
        usuarioUser.setRole(roleUser);
        usuarioUser.setAuthorities(Set.of(authorityRead));

        usuarioService.save(usuarioAdmin);
        usuarioService.save(usuarioUser);

        authorityCreate.setUsuario(usuarioAdmin);
        authorityRepository.save(authorityCreate);
        authorityRead.setUsuario(usuarioAdmin);
        authorityRepository.save(authorityRead);
        authorityRead.setUsuario(usuarioUser);
        authorityRepository.save(authorityRead);
    }
}