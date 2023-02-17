package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.service.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trabajo")
public class TrabajoController {
    @Autowired
    private TrabajoService trabajoService;

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
        Trabajo trbj = trabajoService.findById(id);
        model.addAttribute("trabajo",trbj);
        return "trabajo/actualizarTrabajo";
    }
    // Delete
    @GetMapping("/delete/{id}")
    public String eliminarTrabajo(@PathVariable Integer id) {
        trabajoService.deleteTrabajo(id);
        return "trabajo/eliminarTrabajo";
    }
}
