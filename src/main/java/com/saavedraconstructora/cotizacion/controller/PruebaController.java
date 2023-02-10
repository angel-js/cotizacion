package com.saavedraconstructora.cotizacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

    @GetMapping
    public String home(){
        return "Hello World";
    }

    @GetMapping("/user")
    public String homeUser(){
        return "Hello User";
    }
    @GetMapping("/admin")
    public String homeAdmin(){
        return "Hello Admin!";
    }
}
