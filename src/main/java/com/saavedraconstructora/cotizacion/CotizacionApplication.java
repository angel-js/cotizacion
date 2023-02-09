package com.saavedraconstructora.cotizacion;

import com.saavedraconstructora.cotizacion.service.UserDetailsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class CotizacionApplication {

	@Bean
	public UserDetailsServiceImpl userDetailsServiceImpl() {
		return new UserDetailsServiceImpl();
	}
	public static void main(String[] args) {
		SpringApplication.run(CotizacionApplication.class, args);
	}

}
