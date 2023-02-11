package com.saavedraconstructora.cotizacion.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {

    public String encode(String password) {
        return passwordEncoder().encode(password);
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Boolean isValidPassword(String password, String password2) {
        System.out.println("PASSWORD -------------->>>>> "+ password);
        System.out.println("PASSWORD 2 -------------->>>>> "+ password2);
        return passwordEncoder().matches(password, password2);
    }
}