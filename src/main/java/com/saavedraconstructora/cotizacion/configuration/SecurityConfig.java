package com.saavedraconstructora.cotizacion.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${USUARIO}")
    private String usuario;

    @Value("${PASSWORD}")
    private String password;

    @Value("${ROL}")
    private String roles;
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        System.out.println("Valor de usuario: " + usuario);
        System.out.println("Valor de password: " + password);
        System.out.println("Valor de roles: " + roles);
        if (usuario == null) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo");
        }
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username(usuario)
                .password(password)
                .roles(roles)
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
        @Bean
        protected SecurityFilterChain configure (HttpSecurity http) throws Exception {
            return http
                    .csrf(csrf -> csrf.disable())
                    .authorizeRequests(auth -> {
                                auth.antMatchers("/home").permitAll();
                                auth.antMatchers("/user").hasRole("USER");
                                auth.antMatchers("/admin").hasRole("ADMIN");
                            }
                    ).httpBasic(Customizer.withDefaults())
                    .build();
        }
    }
