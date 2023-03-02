package com.saavedraconstructora.cotizacion.service;

import com.saavedraconstructora.cotizacion.exception.ResourceNotFoundException;
import com.saavedraconstructora.cotizacion.model.*;
import com.saavedraconstructora.cotizacion.repository.*;
import com.sun.jdi.IntegerValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TrabajoService {
    private static final Logger log = LoggerFactory.getLogger(CotizacionService.class);
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private TrabajoRepository trabajoRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void actualizarItem(Integer id, String nombre, int monto) {
        String query = "UPDATE cotizacion.item SET nombre = ?, monto = ? WHERE id = ?";
        jdbcTemplate.update(query, nombre, monto, id);
    }

    public Trabajo findById(Integer id) {
        return trabajoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id ", "id", id));
    }

    public void deleteTrabajo(Integer id) {
        trabajoRepository.deleteById(id);
    }

    public List<Departamento> buscarDepart() {
        log.info("Cotizacion Service: buscarDepart");
        return departamentoRepository.buscarTodos();
    }

    //TODO create a method can search for mail and return usuario (OBJECT)

    // Devolver data USUARIO segun su EMAIL
    public Usuario buscarUsuarioXMail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /* STATUS */
    public List<Status> findAllStatus() {
        log.info("Cotizacion Service: Buscar Status");
        return statusRepository.findAllStatus();
    }

    // Traer todos los supervisores
    public List<Supervisor> findAllSupervisores(){
        return supervisorRepository.findAll();
    }

    // Guardar un TRABAJO
    public Trabajo guardarTrabajo(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    // Buscar supervisor por departamento
    public List<Supervisor> buscarSupervisoresPorDepartamento(Integer idDepartamento) {
        //Integer id = Integer.parseInt(idDepartamento);
        Departamento departamento = departamentoRepository.findById(idDepartamento).orElse(null);
        if (departamento == null) {
            return Collections.emptyList();
        }
        return supervisorRepository.findByDepartamentos_Id(idDepartamento);
    }
    // Buscar Trabajo por status
    public List<Trabajo> findByStatusContaining(Integer status, Integer usuarioId) {
       return trabajoRepository.findByStatusAndUsuario(status, usuarioId);
    }
    // Buscar Trabajo por Status para el ADMIN con todos los Usuarios
    public List<Trabajo> findByStatusAdmin(Integer status) {
        return  trabajoRepository.findByStatus(status);
    }
    // ELIMINAR Trabajo
    public void DeleteByID(Integer id){
        trabajoRepository.deleteById(id);
    }

    // Guardar Item
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    // Buscar Items según trabajo_id
    public List<Item> findByTrabajoId(Integer id) {
        return itemRepository.findByTrabajoId(id);
    }

    public void update(Trabajo trabajoUdp) {
        try {
            Optional<Trabajo> trbjOpt = trabajoRepository.findById(trabajoUdp.getId());
            if (trbjOpt.isPresent()) {
                Trabajo trbjAct = trbjOpt.get();
                trbjAct.setDepartamento(trabajoUdp.getDepartamento());
                trbjAct.setItems(trabajoUdp.getItems());
                trabajoRepository.save(trbjAct);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
