package com.saavedraconstructora.cotizacion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping("/")
    public String homeList() {
        log.info("This is the home page");
        return "Home";
    }

}
