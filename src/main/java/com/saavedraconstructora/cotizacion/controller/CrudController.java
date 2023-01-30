package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.service.CrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class CrudController {

    private CrudService crudService;
    private static final Logger LOG = Logger.getLogger(CrudController.class);
    public CrudController(CrudService crudService) {
        this.crudService = crudService;
    }

    @RequestMapping("/buscar")
    public String buscarCotizaciones(Model model) {
        List<Cotizacion> todos = crudService.buscar();
        model.addAttribute("cotizacion", todos);
        LOG.debug("Retorno de Lista en HTML con JPA");
        return "cotizacionlist";
    }

    @RequestMapping("/crear")
    public String crearCotizaciones() {
        return "crearCotizacion";
    }
}
