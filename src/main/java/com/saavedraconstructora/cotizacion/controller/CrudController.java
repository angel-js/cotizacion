package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.domain.Departamento;
import com.saavedraconstructora.cotizacion.service.CrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/cotizacion")
public class CrudController {

    private CrudService crudService;
        public CrudController(CrudService crudService) {
        this.crudService = crudService;
    }

    @RequestMapping("/buscar")
    public String buscarCotizaciones(Model model) {
        List<Cotizacion> todos = crudService.buscar();
        model.addAttribute("cotizacion", todos);
        System.out.println("Retorno de Lista en HTML con JPA");
        return "cotizacionlist";
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
}
