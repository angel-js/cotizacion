package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.repository.CotizacionRepository;
import com.saavedraconstructora.cotizacion.service.CotizacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cotizacion")
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

    @RequestMapping("/buscar")
    public String buscarCotizaciones(Model model) {
        log.debug("Search of all cotizaciones PATH:/buscar");
        List<Cotizacion> todos = cotizacionService.buscar();
        model.addAttribute("cotizacion", todos);
        if(!todos.isEmpty()){
        log.debug("Retorno de Lista en HTML con JPA");
        return "CotizacionList";
        } else {
            log.debug("The request is empty!");
            return "CotizacionErrorView";
        }
    }

    @RequestMapping("/busquedaConParametros")
    public String busqueda(@RequestParam("q") String consulta, Model model) {
        log.debug("Search of cotizacion with any parameter PATH:/busquedaConParametros");
        log.debug("The parameter is "+ consulta);
        List<Cotizacion> todos = cotizacionService.busqueda(consulta);
        model.addAttribute("cotizacion", todos);
        model.addAttribute("consulta", consulta);
        if(!todos.isEmpty()){
            log.debug("Return a list of JPA parameters");
            return "CotizacionBuscado";
        } else {
            log.debug("The request is empty!");
            log.debug("The Request has empty parameters");
            return "CotizacionErrorView";
        }
    }

    @RequestMapping("/crear")
    public String crearCotizaciones(Model model) {
        log.debug("Create of cotizacion PATH:/crear");
        model.addAttribute("departamentos", cotizacionService.buscarDepart());
        model.addAttribute("cotizacion", new Cotizacion());
        return "CotizacionCreate";
    }

    @PostMapping("/guardar")
    public String guardar(Cotizacion cotizacion) {
        log.debug("Save of cotizacion PATH:/crear ---> " + cotizacion);cotizacionService.guardar(cotizacion);
        return "redirect:/home/";
    }

    @RequestMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        Optional<Cotizacion> cotOptional = cotizacionService.findById(id);
        log.debug("COTIZACION OBJECT BEFORE JPA ------------> " + cotOptional);
        if (cotOptional.isPresent()) { // Se valida si el objeto viene en el array y se desempaqueta
            log.debug("The object is ready to inyect and is not empty");
            Cotizacion cot = cotOptional.get(); // Para ser pasado en nuevo objeto
            model.addAttribute("cotizacion", cot); // Y finalmente mostrado en la vista
            return "CotizacionDetalle";
        } else {
            return "CotizacionErrorView";
        }
    }
}
