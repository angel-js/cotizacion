package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.service.AuthenticationService;
import com.saavedraconstructora.cotizacion.service.UsuarioServicioImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private AuthenticationService authenticationService;

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @PostMapping("/login")
    public String authenticate(@RequestParam String username, @RequestParam String password,
                               HttpServletRequest request) throws ServletException {
        System.out.println("ENTRE -----------------");
        boolean authenticated = authenticationService.authenticate(username, password);
        if (authenticated) {
            System.out.println("Auntenticado -----------------");
            request.login(username, password);
            return "home/Home";
        } else {
            return "home/HomeLoginV";
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "home/HomeLogin";
    }

    @GetMapping("/")
    public String redirect(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/admin/cotizacion/home";
        } else {
            return "redirect:/user/home";
        }
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("VER EL FORMULARIO -----------------");
        return "home/HomeLoginV";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, null, auth);
        }
        return "redirect:/login?logout";
    }
}
