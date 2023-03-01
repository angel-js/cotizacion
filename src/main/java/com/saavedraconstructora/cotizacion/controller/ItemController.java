package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Item;
import com.saavedraconstructora.cotizacion.model.ItemForm;
import com.saavedraconstructora.cotizacion.repository.ItemRepository;
import com.saavedraconstructora.cotizacion.service.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/trabajo/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TrabajoService trabajoService;

    @PostMapping("/update")
    public ResponseEntity<String> update(@ModelAttribute("itemForm") ItemForm itemForm) {
        for (Item item : itemForm.getItems()) {
            System.out.println("item ---->>>>>>>> " + item);
            trabajoService.actualizarItem(item.getId(), item.getNombre(), item.getMonto());
        }
        return ResponseEntity.ok().body("redirect:/admin/trabajo/home");
    }
}
