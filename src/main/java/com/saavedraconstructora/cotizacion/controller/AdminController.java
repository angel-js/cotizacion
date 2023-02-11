package com.saavedraconstructora.cotizacion.controller;

import java.util.List;

import com.saavedraconstructora.cotizacion.dto.UsuarioDto;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.repository.UsuarioRepository;
import com.saavedraconstructora.cotizacion.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

    @Autowired
    private AdminService adminService;
    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String homeUsers(Model model) {
        return "AdminHome";
    }

    @GetMapping("/busquedaPersonalizada")
    public String SearchUsers(Model model) {
        return "AdminList";
    }

    @GetMapping("/crear")
    public String CreateUser(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarioDto", new UsuarioDto());
        model.addAttribute("roles", adminService.RolefindAll());
        return "AdminCreateUsers";
    }

    @PostMapping("/guardar")
    public String SaveUser(Model model) { return "redirect:/admin/users/"; }
}
