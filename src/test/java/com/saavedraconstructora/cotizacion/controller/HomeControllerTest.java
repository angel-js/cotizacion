package com.saavedraconstructora.cotizacion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void homeList() {
        HomeController homeController = new HomeController();
        String result = homeController.homeList();
        assertEquals("home", result);
    }
}