package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.domain.Cotizacion;
import com.saavedraconstructora.cotizacion.domain.Supervisor;
import com.saavedraconstructora.cotizacion.repository.SupervisorRepository;
import com.saavedraconstructora.cotizacion.service.CrudService;
import com.saavedraconstructora.cotizacion.service.PersonalService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalService personalService;
    private final SupervisorRepository supervisorRepository;
    private final CrudService crudService;

    public PersonalController(PersonalService personalService, SupervisorRepository supervisorRepository,
                              CrudService crudService) {
        this.personalService = personalService;
        this.supervisorRepository = supervisorRepository;
        this.crudService = crudService;
    }

    @RequestMapping("/buscar")
    public String buscarPersonal(Model model) {
        List<Supervisor> supervisores = personalService.buscar();
        model.addAttribute("supervisores", supervisores);
        return "buscarPersonal";
    }

    @RequestMapping("/busquedaPersonalizada")
    public String busqueda(@RequestParam("q") String consulta, Model model) {
        List<Supervisor> todos = personalService.busqueda(consulta);
        model.addAttribute("supervisores", todos);
        if(!todos.isEmpty()){
            System.out.println("Retorno de Lista en HTML con JPA con Parametros");
            return "buscarPersonalPersonalizado";
        } else {
            System.out.println("La Consulta esta vacía parametro buscar");
            return "errorViewPersonal";
        }
    }

    @RequestMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        Optional<Supervisor> supervisorOptional = supervisorRepository.findById(id);
        System.out.println("COTIZACION OBJECT BEFORE JPA ------------> " + supervisorOptional);
        if (supervisorOptional.isPresent()) { // Se valida si el objeto viene en el array y se desempaqueta
            Supervisor supervisor = supervisorOptional.get(); // Para ser pasado en nuevo objeto
            model.addAttribute("supervisor", supervisor); // Y finalmente mostrado en la vista
            return "verMasDetallePersonal";
        } else {
            return "errorView";
        }
    }

    // Crear un Supervisor
    @RequestMapping("/crear")
    public String crearSupervisor(Model model) {
        model.addAttribute("departamentos", crudService.buscarDepart() );
        model.addAttribute("supervisor", new Supervisor());
        return "crearSupervisor";
    }

    // Guardar un Personal
    @PostMapping("/guardar")
    public String guardar(Supervisor supervisor) {
        System.out.println("Se guarda la informacion del Supervisor  ------> " + supervisor);
        personalService.guardar(supervisor);
        return "redirect:/personal/buscar";
    }
}
