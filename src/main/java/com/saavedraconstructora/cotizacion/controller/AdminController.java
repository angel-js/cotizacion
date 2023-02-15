package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import com.saavedraconstructora.cotizacion.service.AdminService;
import com.saavedraconstructora.cotizacion.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin/users")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RegistroService registroService;
    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String homeUsers(Model model) {
        return "admin/AdminHome";
    }

    @GetMapping("/busquedaPersonalizada")
    public String SearchUsers(Model model) {
        return "admin/AdminList";
    }

    @GetMapping("/crear")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("roles",registroService.mostrarRoles() );
        model.addAttribute("usuario", new Usuario());
        return "registro/RegistroHome";
    }

}
