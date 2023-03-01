package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.*;
import com.saavedraconstructora.cotizacion.repository.ItemRepository;
import com.saavedraconstructora.cotizacion.repository.TrabajoRepository;
import com.saavedraconstructora.cotizacion.service.TrabajoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/trabajo")
public class TrabajoController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private TrabajoService trabajoService;
    private boolean itemsAgregados = false;
    @Autowired
    private TrabajoRepository trabajoRepository;
    @Autowired
    private ItemRepository itemRepository;

    //HOME VIEW
    @GetMapping("/home")
    public String homeTrabajo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = trabajoService.buscarUsuarioXMail(auth.getName());
        model.addAttribute("user", user);
        return "trabajo/homeTrabajo";
    }

    // -------- CRUD --------
    // Read and Search
    @GetMapping("/list")
    public String listarTrabajo() {
        return "trabajo/listarTrabajo";
    }

    @PostMapping("/busquedaConParametros")
    public String busquedaConParametros(@RequestParam("status") Integer status, Model model) {
        try {
            List<Trabajo> trabajos = trabajoService.findByStatusAdmin(status);
            model.addAttribute("trabajos", trabajos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "trabajo/busquedaConParametros";
    }

    // DETALLE de cada Trabajo
    @GetMapping("/detalle/{id}")
    public String trabajoDetalleAdmin(@PathVariable Integer id, Model model) {
        Trabajo trbj = trabajoService.findById(id);
        System.out.println(trbj.getItems()); // Agrega esta línea para verificar que la lista se está cargando correctamente
        model.addAttribute("trabajo", trbj);
        return "trabajo/detalleTrabajo";
    }

    //UPDATE
    @GetMapping("/update/{id}")
    public String actualizarTrabajo(@PathVariable Integer id, Model model) {
        // Obtener lista de Items según trabajoID
        List<Item> items = trabajoService.findByTrabajoId(id);
        // Crear objeto ItemForm y asignar la lista de items
        ItemForm itemForm = new ItemForm();
        itemForm.setItems(items);
        System.out.println(itemForm.getItems());
        // Agregar itemForm y Trabajo al modelo
        model.addAttribute("itemForm", itemForm);
        model.addAttribute("trabajo", trabajoService.findById(id));
        // Devolver la vista con el modelo
        return "trabajo/actualizarTrabajo";
    }

    @PostMapping("/update/{id}")
    public String actualizarTrabajo(@PathVariable Integer id, @ModelAttribute("trabajo") Trabajo trabajoUpd,
                                    @RequestParam(name = "nombre[]", required = false) String[] nombres,
                                    @RequestParam(name = "monto[]", required = false) Integer[] montos) {
// Obtener los items originales del trabajo a actualizar desde la base de datos
        Trabajo trabajoOriginal = trabajoRepository.findById(id).orElseThrow();
        List<Item> nuevosItems = new ArrayList<>();
        if (nombres != null && montos != null) {
            // Comparar los items originales con los nuevos items enviados desde el formulario
            for (int i = 0; i < nombres.length; i++) {
                String nombre = nombres[i];
                Integer monto = montos[i];
                if (nombre != null && !nombre.isEmpty() && monto != null) {
                    Item item = new Item();
                    log.info("Item: " + item);
                    item.setNombre(nombre);
                    item.setMonto(monto);
                    item.setTrabajo(trabajoUpd);
                    // Para cada item nuevo o actualizado, guardar o actualizar el objeto Item en la base de datos
                    itemRepository.save(item);
                    nuevosItems.add(item);
                }
            }
        }
    // Para cada item original que no se encuentre en la lista de nuevos items, eliminarlo de la base de datos
        for (Item item : trabajoOriginal.getItems()) {
            if (!nuevosItems.contains(item)) {
                itemRepository.delete(item);
            }
        }
    // Asignar la lista de nuevos items al trabajo a actualizar
        trabajoUpd.setItems(nuevosItems);
    // Guardar el trabajo en la base de datos
        trabajoService.update(trabajoUpd);
        return "redirect:/admin/trabajo/detalle/" + id;
    }

    /**
     * @PostMapping("/update/{id}") public String actualizarTrabajo(@PathVariable Integer id, @ModelAttribute("trabajo") Trabajo trabajoUpd,
     * List<Item> items) {
     * // Iterar sobre la lista de items y guardar cada uno
     * for(Item item : items) {
     * itemRepository.save(item);
     * }
     * // Ejecutar la actualización en la base de datos
     * trabajoRepository.save(trabajoUpd);
     * return "redirect:/admin/trabajo/detalle/__${trabajoUpd.id}__";
     * }
     */

    // Delete
    @PostMapping("/delete/{id}")
    public String eliminarTrabajo(@PathVariable Integer id) {
        trabajoService.deleteTrabajo(id);
        return "redirect:/admin/trabajo/home";
    }
}
