package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.domain.Departamento;
import com.saavedraconstructora.cotizacion.repository.CotizacionRepository;
import com.saavedraconstructora.cotizacion.service.CrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/cotizacion")
public class CrudController {

    private CrudService crudService;
    private final CotizacionRepository cotizacionRepository;

    public CrudController(CrudService crudService,
                          CotizacionRepository cotizacionRepository) {
        this.crudService = crudService;
        this.cotizacionRepository = cotizacionRepository;
    }

    @RequestMapping("/buscar")
    public String buscarCotizaciones(Model model) {
        List<Cotizacion> todos = crudService.buscar();
        model.addAttribute("cotizacion", todos);
        System.out.println("Retorno de Lista en HTML con JPA");
        return "cotizacionlist";
    }

    @RequestMapping("/busquedaConParametros")
    public String busqueda(@RequestParam("q") String consulta, Model model) {
        List<Cotizacion> todos = crudService.busqueda(consulta);
        model.addAttribute("cotizacion", todos);
        if(!todos.isEmpty()){
            System.out.println("Retorno de Lista en HTML con JPA con Parametros");
            return "cotizacionlist";
        } else {
            System.out.println("La Consulta esta vacía parametro busca");
            return "errorView";
        }
    }

    @RequestMapping("/crear")
    public String crearCotizaciones(Model model) {
        model.addAttribute("departamentos", crudService.buscarDepart());
        model.addAttribute("cotizacion", new Cotizacion());
        return "crearCotizacion";
    }

    @PostMapping("/guardar")
    public String guardar(Cotizacion cotizacion) {
        System.out.println("Se guarda cotizacion  ------> " + cotizacion);
        crudService.guardar(cotizacion);
        return "redirect:/home/";
    }

    @RequestMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        Optional<Cotizacion> cotOptional = crudService.findById(id);
        System.out.println("COTIZACION OBJECT BEFORE JPA ------------> " + cotOptional);
        if (cotOptional.isPresent()) { // Se valida si el objeto viene en el array y se desempaqueta
            Cotizacion cot = cotOptional.get(); // Para ser pasado en nuevo objeto
            model.addAttribute("cotizacion", cot); // Y finalmente mostrado en la vista
            return "verMasDetalle";
        } else {
            return "errorView";
        }
    }
}
