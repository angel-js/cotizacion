package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    @Autowired
    private TrabajoService trabajoService;
    //HOME VIEW
    @GetMapping("/home")
    public String homeTrabajo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
        model.addAttribute("user", user); // TODO make a Service Method with only NAME and LASTNAME
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
        return "usuario/listarTrabajo";
    }

    @PostMapping("/busquedaConParametros")
    public String busquedaConParametros(@RequestParam("status") Integer status, Model model) {
        try {
            log.info("Search of Trabajo with any parameter PATH:/busquedaConParametros");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
            log.info("USUARIO ------------------------------------ ID: " + user.getId());
            List<Trabajo> trabajos = trabajoService.findByStatusContaining(status, user.getId());
            if (trabajos.isEmpty()) {
                log.debug("The request is empty!");
                log.debug("The Request has empty parameters");
                return "redirect:/user/list";
            } else {
                model.addAttribute("trabajos", trabajos);
                return "usuario/resultadoParametros";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.debug("Hay un error al intentar buscar los trabajos");
            return "redirect:/user/list";
        }
    }


    /*@GetMapping("/supervisoresPorDepartamento")
    public @ResponseBody List<Supervisor> supervisoresPorDepartamento(@RequestParam Integer idDepartamento) {
        log.info("Entra a EL AJAX SUPERVISORES X DEPARTAMENTOS");
        return trabajoService.buscarSupervisoresPorDepartamento(idDepartamento);
    }*/

    @GetMapping("/create")
    public String crearTrabajo(Model model) {
        log.info("SOY CREATE TRABAJO-----------------");
        Trabajo trabajo = new Trabajo();
        model.addAttribute("trabajo", trabajo);
        model.addAttribute("status",trabajoService.findAllStatus() );
        model.addAttribute("departamentos", trabajoService.buscarDepart());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
        model.addAttribute("usuario", user);
        // No se si me falte instanciar o declarar un objeto de tipo ITEM
        return "usuario/crearTrabajo";
    }

    // Update
    @GetMapping("trabajo/update/{id}")
    public String actualizarTrabajo(@PathVariable Integer id, Model model) {
        Trabajo trbj = trabajoService.findById(id);
        model.addAttribute("trabajo",trbj);
        return "usuario/actualizarTrabajo";
    }

    // SAVE
    @PostMapping("/guardar")
    public String guardarTrabajos(@Valid Trabajo trabajo, BindingResult bindingResult, Model model,
                                  HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("status", trabajoService.findAllStatus());
            model.addAttribute("departamentos", trabajoService.buscarDepart());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
            model.addAttribute("usuario", user);
            return "usuario/crearTrabajo";
        }
        if(trabajo.getUsuario() == null || trabajo.getSupervisor() == null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
            trabajo.setUsuario(user);
            List<Supervisor> supervisores = trabajoService.buscarSupervisoresPorDepartamento(
                    trabajo.getDepartamento().getId());
            trabajo.setSupervisor(supervisores.get(0));
        }
        // obtener los items enviados desde el formulario
        String[] nombres = request.getParameterValues("nombre");
        String[] montos = request.getParameterValues("monto");

        // agregar cada item al trabajo
        for (int i = 0; i < nombres.length; i++) {
            String nombre = nombres[i];
            int monto = Integer.parseInt(montos[i]);
            Item item = new Item();
            item.setNombre(nombre);
            item.setMonto(monto);
            trabajo.addItem(item);
        }
        trabajoService.guardarTrabajo(trabajo);
        return "redirect:/user/trabajo";
    }

    // DETALLE
    @GetMapping("/trabajo/detalle/{id}")
    public String detalleTrabajo(@PathVariable Integer id, Model model){
        Trabajo trbj = trabajoService.findById(id);
        model.addAttribute("trabajo", trbj);
        return "usuario/detalleTrabajo"; /*TODO debo hacer un response para cuando sea vacion con una validacion*/
    }

    // DELETE
    @GetMapping("trabajo/delete/{id}")
    public String eliminarTrabajo(@PathVariable Integer id) {
        trabajoService.DeleteByID(id);
        log.info("TRABAJO ID DELETE: -------------------> " + id);
        return "redirect:/user/trabajo";
    }
}

/*List<Item> items = new ArrayList<>();
        int count = Integer.parseInt(request.getParameter("itemCount"));
        for (int i = 0; i < count; i++) {
            String nombre = request.getParameter("items[" + i + "].nombre");
            int monto = Integer.parseInt(request.getParameter("items[" + i + "].monto"));
            items.add(new Item(nombre, monto, trabajo));
        }
        trabajo.setItems(items);
        */