package com.saavedraconstructora.cotizacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/find")
public class FindController {
    
    @RequestMapping("/")
    public String buscarCotizaciones() {
        return "cotizacionlist";
    }
}
