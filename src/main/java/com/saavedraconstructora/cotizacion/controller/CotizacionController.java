package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Cotizacion;
import com.saavedraconstructora.cotizacion.model.Supervisor;
import com.saavedraconstructora.cotizacion.repository.CotizacionRepository;
import com.saavedraconstructora.cotizacion.service.CotizacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/cotizacion")
public class CotizacionController {

    //Injection of dependencies
    private CotizacionService cotizacionService;
    private final CotizacionRepository cotizacionRepository;
    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    public CotizacionController(CotizacionService cotizacionService,
                                CotizacionRepository cotizacionRepository) {
        this.cotizacionService = cotizacionService;
        this.cotizacionRepository = cotizacionRepository;
    }

    /* READ */

    @RequestMapping("/home")
    public String homePage(){
        return "Home";
    }

    @RequestMapping("/buscar")
    public String buscarCotizaciones(Model model) {
        log.info("Search of all cotizaciones PATH:/buscar");
        log.info("Retorno de Lista en HTML con JPA");
        return "CotizacionList";
    }

    @RequestMapping("/busquedaConParametros")
    public String busqueda(@RequestParam("q") String consulta, Model model) {
        log.info("Search of cotizacion with any parameter PATH:/busquedaConParametros");
        log.info("The parameter is "+ consulta);
        List<Cotizacion> todos = cotizacionService.busqueda(consulta);
        model.addAttribute("cotizacion", todos);
        model.addAttribute("consulta", consulta);
        if(!todos.isEmpty()){
            log.info("Return a list of JPA parameters");
            return "CotizacionBuscado";
        } else {
            log.debug("The request is empty!");
            log.debug("The Request has empty parameters");
            return "CotizacionErrorView";
        }
    }
    @RequestMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        Optional<Cotizacion> cotOptional = cotizacionService.findById(id);
        log.info("COTIZACION OBJECT BEFORE JPA ------------> " + cotOptional);
        if (cotOptional.isPresent()) { // Se valida si el objeto viene en el array y se desempaqueta
            log.info("The object is ready to inyect and is not empty");
            Cotizacion cot = cotOptional.get(); // Para ser pasado en nuevo objeto
            model.addAttribute("cotizacion", cot); // Y finalmente mostrado en la vista
            return "CotizacionDetalle";
        } else {
            log.debug("The object is empty");
            return "CotizacionErrorView";
        }
    }
    /* CREATE */
    @RequestMapping("/crear")
    public String crearCotizaciones(Model model) {
        log.info("Create of cotizacion PATH:/crear");
        model.addAttribute("departamentos", cotizacionService.buscarDepart());
        model.addAttribute("cotizacion", new Cotizacion());
        return "CotizacionCreate";
    }

    @PostMapping("/guardar")
    public String guardar(Cotizacion cotizacion) {
        log.info("Save of cotizacion PATH:/crear ---> " + cotizacion);cotizacionService.guardar(cotizacion);
        return "redirect:/admin/home/";
    }

    /* UPDATE */
    @GetMapping("/update/{id}")
    public String showUpdateCotizacionForm(@PathVariable Integer id, Model model) {
        log.info("UPDATE of cotizacion PATH:/update/{id}");
        Optional<Cotizacion> cotizacion = cotizacionService.findById(id);
        if (cotizacion.isPresent()){
            Cotizacion cot = cotizacion.get();
            model.addAttribute("cotizacion", cot);
            model.addAttribute("departamentos", cotizacionService.buscarDepart());
            return "CotizacionUpdateForm";
        } else {
            log.debug("The object is empty");
            return "CotizacionErrorView";
        }
    }

    @PostMapping("/update/{id}")
    public String updateCotizacion(@PathVariable Integer id, @ModelAttribute Cotizacion cotizacion) {
        log.info("This is save instance of supervisor PATH: /update/{id}");
        log.info("Update info  ------> " + cotizacion);
        cotizacionService.update(id, cotizacion);
        return "redirect:/admin/cotizacion/buscar";
    }

    /* DELETE */
    @PostMapping("/delete/{id}")
    public String deleteCotizacion(@PathVariable Integer id, @RequestParam(required = false) String _method) {
        if ("delete".equals(_method)) { /* If delete comes in HTML method execute this code*/
            log.info("This is delete of Cotizacion PATH: /delete/{id}");
            log.info("Cotizacion supervisor of DATABASE  ------> " + id);
            cotizacionService.deleteCotizacion(id);
            return "redirect:/admin/cotizacion/buscar";
        } else {
            log.debug("Error to eliminate  ------> " + id);
            return "Error 404";
        }
    }

}
