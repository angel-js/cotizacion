package com.saavedraconstructora.cotizacion.service;

import java.util.List;
import com.saavedraconstructora.cotizacion.model.Role;
import com.saavedraconstructora.cotizacion.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> RolefindAll() {
        return roleRepository.findAll();
    }
}
