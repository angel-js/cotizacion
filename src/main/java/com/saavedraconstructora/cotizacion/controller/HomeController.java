package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.dto.UsuarioDto;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.service.AdminService;
import com.saavedraconstructora.cotizacion.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UsuarioService usuarioService;

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping("/")
    public String homeList() {
        log.info("This is the home page");
        return "HomeLogin";
    }

    @RequestMapping("/login")
    public String login() {
        log.info("This is a Login page");
        return "HomeLoginPage";
    }
    @GetMapping("/register")
    public String CreateUser(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarioDto", new UsuarioDto());
        model.addAttribute("roles", adminService.RolefindAll());
        return "AdminCreateUsers";
    }

    @PostMapping("/guardar")
    public String SaveUser(@ModelAttribute("usuarioDto") UsuarioDto usuarioDto, Model model) {
        if (!usuarioDto.getPassword().equals(usuarioDto.getPassword2())) {
            model.addAttribute("errM", "Las contraseñas no coinciden");
            model.addAttribute("usuarioDto", usuarioDto);
            model.addAttribute("roles", adminService.RolefindAll());
            return "AdminCreateUsers";
        } else {
            Usuario usuario = new Usuario();
            usuario.setUsername(usuarioDto.getUsername());
            usuario.setPassword(usuarioDto.getPassword());
            usuario.setRole(usuarioDto.getRole());
            adminService.saveUser(usuario);
            return "redirect:/admin/users/";
        }
    }
}
