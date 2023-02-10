package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

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
}
