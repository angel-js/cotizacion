package com.saavedraconstructora.cotizacion.controller;

import java.util.List;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    @GetMapping("/")
    public String homeUsers(Model model) {
        return "AdminHome";
    }

    @GetMapping("/busquedaPersonalizada")
    public String SearchUsers(Model model) {
        return "AdminList";
    }
}
