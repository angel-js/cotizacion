package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Item;
import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.service.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/trabajo")
public class TrabajoController {
    @Autowired
    private TrabajoService trabajoService;

    //HOME VIEW
    @GetMapping("/home")
    public String homeTrabajo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
        model.addAttribute("user", user);
        return "trabajo/homeTrabajo";
    }
    // -------- CRUD --------
    // Read and Search
    @GetMapping("/list")
    public String listarTrabajo() {
        return "trabajo/listarTrabajo";
    }
    @PostMapping("/busquedaConParametros")
    public String busquedaConParametros(@RequestParam("status") Integer status, Model model) {
        try {
            List<Trabajo> trabajos = trabajoService.findByStatusAdmin(status);
            model.addAttribute("trabajos", trabajos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "trabajo/busquedaConParametros";
    }

    // DETALLE de cada Trabajo
    @GetMapping("/detalle/{id}")
    public String trabajoDetalleAdmin(@PathVariable Integer id, Model model) {
        Trabajo trbj = trabajoService.findById(id);
        System.out.println(trbj.getItems()); // Agrega esta línea para verificar que la lista se está cargando correctamente
        model.addAttribute("trabajo", trbj);
        return "trabajo/detalleTrabajo";
    }

    // Update
    @GetMapping("/update/{id}")
    public String actualizarTrabajo(@PathVariable Integer id, Model model) {
        // Lista de Items según trabajoID
        List<Item> items = trabajoService.findByTrabajoId(id);
        System.out.println("ITEMS --------------> " + items);
        Trabajo trbj = trabajoService.findById(id);
        model.addAttribute("trabajo",trbj);
        model.addAttribute("departamentos", trabajoService.buscarDepart());
        Usuario user = trabajoService.buscarUsuarioXMail(trbj.getUsuario().getEmail());
        model.addAttribute("usuario", user);
        model.addAttribute("status", trabajoService.findAllStatus());
        return "trabajo/actualizarTrabajo";
    }
    // Delete
    @GetMapping("/delete/{id}")
    public String eliminarTrabajo(@PathVariable Integer id) {
        trabajoService.deleteTrabajo(id);
        return "trabajo/eliminarTrabajo";
    }
}
