package com.saavedraconstructora.cotizacion.configuration;

import com.saavedraconstructora.cotizacion.service.EncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    private EncoderService encoderService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/home/").permitAll()
                .antMatchers("/home/register").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/home/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoderService.passwordEncoder());
    }

}


    /**

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
    } **/
