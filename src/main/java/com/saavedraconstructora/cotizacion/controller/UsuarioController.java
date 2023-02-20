package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Departamento;
import com.saavedraconstructora.cotizacion.model.Supervisor;
import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.service.TrabajoService;
import com.saavedraconstructora.cotizacion.service.UsuarioRolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    @Autowired
    private UsuarioRolService usuarioRolService;

    @Autowired
    private TrabajoService trabajoService;


    //HOME VIEW
    @GetMapping("/home")
    public String homeTrabajo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
        model.addAttribute("nombre", user); // TODO make a Service Method with only NAME and LASTNAME
        return "usuario/homeUsuario";
    }
    // -------- CRUD --------
    // Read
    @GetMapping("/trabajo")
    public String trabajoView() {
        return "usuario/trabajoView";
    }

    @GetMapping("/list")
    public String listarTrabajo(Model model) {
        return "trabajo/listarTrabajo";
    }

    /*@GetMapping("/supervisoresPorDepartamento")
    public @ResponseBody List<Supervisor> supervisoresPorDepartamento(@RequestParam Integer idDepartamento) {
        log.info("Entra a EL AJAX SUPERVISORES X DEPARTAMENTOS");
        return trabajoService.buscarSupervisoresPorDepartamento(idDepartamento);
    }*/

    @GetMapping("/create")
    public String crearTrabajo(Model model) {
        log.info("SOY CREATE TRABAJO-----------------");
        model.addAttribute("trabajo", new Trabajo());
        model.addAttribute("status",trabajoService.findAllStatus() );
        model.addAttribute("departamentos", trabajoService.buscarDepart());
        //model.addAttribute("supervisor", trabajoService.findAllSupervisores());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
        model.addAttribute("usuario", user);
        return "usuario/crearTrabajo";
    }

    // Update
    @GetMapping("/update/{id}")
    public String actualizarTrabajo(@PathVariable Integer id, Model model) {
        Trabajo trbj = usuarioRolService.findById(id);
        model.addAttribute("trabajo",trbj);
        return "trabajo/actualizarTrabajo";
    }

    // SAVE
    @PostMapping("/guardar")
    public String guardarTrabajos(@Valid Trabajo trabajo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Si hay errores de validación, regresa a la página de crear trabajo
            model.addAttribute("status", trabajoService.findAllStatus());
            model.addAttribute("departamentos", trabajoService.buscarDepart());
            //model.addAttribute("supervisor", trabajoService.findAllSupervisores());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
            model.addAttribute("usuario", user);
            return "usuario/crearTrabajo";
        }
        if(trabajo.getUsuario() == null || trabajo.getSupervisor() == null){
            // Añadir usuario
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
            trabajo.setUsuario(user);
            // Añadir supervisor
            List<Supervisor> supervisores = trabajoService.buscarSupervisoresPorDepartamento(
                    trabajo.getDepartamento().getId());
            // Asingar el primero de la lista
            trabajo.setSupervisor(supervisores.get(0));
        }
        trabajoService.guardarTrabajo(trabajo);
        return "redirect:/user/trabajos";
    }
}
