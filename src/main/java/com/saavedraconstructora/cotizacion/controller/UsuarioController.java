package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.service.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioRolService usuarioRolService;

    //HOME VIEW
    @GetMapping("/home")
    public String homeTrabajo() {
        return "trabajo/homeTrabajo";
    }
    // -------- CRUD --------
    // Read
    @GetMapping("/list")
    public String listarTrabajo() {
        return "trabajo/listarTrabajo";
    }

    // Create
    @GetMapping("/create")
    public String crearTrabajo() {
        return "trabajo/crearTrabajo";
    }
    // Update
    @GetMapping("/update/{id}")
    public String actualizarTrabajo(@PathVariable Integer id, Model model) {
        Trabajo trbj = usuarioRolService.findById(id);
        model.addAttribute("trabajo",trbj);
        return "trabajo/actualizarTrabajo";
    }
}
