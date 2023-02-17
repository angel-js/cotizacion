package com.saavedraconstructora.cotizacion.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /* This Class is for STATIC FILES*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");

        registry.addResourceHandler("/home/**")
                .addResourceLocations("classpath:/templates/home/");

        registry.addResourceHandler("/admin/**")
                .addResourceLocations("classpath:/templates/admin/");

        registry.addResourceHandler("/supervisor/**")
                .addResourceLocations("classpath:/templates/supervisor/");

        registry.addResourceHandler("/cotizacion/**")
                .addResourceLocations("classpath:/templates/cotizacion/");

        registry.addResourceHandler("/registro/**")
                .addResourceLocations("classpath:/templates/registro/");

        registry.addResourceHandler("/trabajo/**")
                .addResourceLocations("classpath:/templates/trabajo/");

        registry.addResourceHandler("/usuario/**")
                .addResourceLocations("classpath:/templates/usuario/");
    }
}
