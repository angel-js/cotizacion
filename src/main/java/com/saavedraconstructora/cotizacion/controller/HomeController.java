package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Authority;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping("/home")
    public String homeList() {
        log.info("This is the home page");
        return "Home";
    }

    @RequestMapping("/login")
    public String login() {
        log.info("This is a Login page");
        return "HomeLoginPage";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Model model) {
        // Verificar si el usuario existe y la contraseña es válida
        Usuario usuario = usuarioService.findByUsernameAndPassword(username, password);
        if (usuario != null) {
            Set<Authority> authorities = usuario.getAuthorities();
            Collection<? extends GrantedAuthority> grantedAuthorities = authorities.stream()
                    .map(a -> (GrantedAuthority) a)
                    .collect(Collectors.toList());
            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, grantedAuthorities);
            Authentication authenticatedUser = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            return "redirect:/home";
        } else {
            model.addAttribute("errorMessage", "Usuario o contraseña inválidos.");
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuario o contraseña inválidos.");
        }
        return "HomeLoginPage";
    }

}
