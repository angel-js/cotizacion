
package com.saavedraconstructora.cotizacion.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FindControllerTest {
    
    @Test
    void buscarCotizaciones() {
        FindController findController = new FindController();
        String result = findController.buscarCotizaciones();
        assertEquals("cotizacionlist", result);
    }
}
