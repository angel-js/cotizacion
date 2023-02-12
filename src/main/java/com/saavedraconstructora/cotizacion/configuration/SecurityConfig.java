package com.saavedraconstructora.cotizacion.configuration;

import com.saavedraconstructora.cotizacion.interfaces.UsuarioServicio;
import com.saavedraconstructora.cotizacion.service.BCryptPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BCryptPasswordService passwordEncoder;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                        "/registro**",
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/admin/cotizacion/home", true) //aqui
                .permitAll()
                .and()
                .logout()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}


/**
 * @Bean protected SecurityFilterChain configure (HttpSecurity http) throws Exception {
 * return http
 * .csrf(csrf -> csrf.disable())
 * .authorizeRequests(auth -> {
 * auth.antMatchers("/home").permitAll();
 * auth.antMatchers("/user").hasRole("USER");
 * auth.antMatchers("/admin").hasRole("ADMIN");
 * }
 * ).httpBasic(Customizer.withDefaults())
 * .build();
 * }
 * @Value("${USUARIO}") private String usuario;
 * @Value("${PASSWORD}") private String password;
 * @Value("${ROL}") private String roles;
 * @Bean public InMemoryUserDetailsManager userDetailsManager() {
 * System.out.println("Valor de usuario: " + usuario);
 * System.out.println("Valor de password: " + password);
 * System.out.println("Valor de roles: " + roles);
 * if (usuario == null) {
 * throw new IllegalArgumentException("El nombre de usuario no puede ser nulo");
 * }
 * UserDetails admin = User.withDefaultPasswordEncoder()
 * .username(usuario)
 * .password(password)
 * .roles(roles)
 * .build();
 * return new InMemoryUserDetailsManager(admin);
 * }
 **/
