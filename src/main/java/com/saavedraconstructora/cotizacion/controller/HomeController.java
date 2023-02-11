package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.dto.UsuarioDto;
import com.saavedraconstructora.cotizacion.model.Usuario;
import com.saavedraconstructora.cotizacion.service.AdminService;
import com.saavedraconstructora.cotizacion.service.EncoderService;
import com.saavedraconstructora.cotizacion.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;


    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String homeList() {
        log.info("This is the home page");
        return "HomeLogin";
    }

    @RequestMapping("/home")
    public String homeView() {
        log.info("This is the home page");
        return "Home";
    }

    @Autowired
    private EncoderService encoderService;

    @PostMapping("/loginIn")
    public String login(Usuario us
                        /** @RequestParam("username") String username,
                         @RequestParam("password") String password**/,
                        Model model) {
        Usuario usuario = usuarioService.findByUsername(us.getUsername());
        if (usuario != null) {
            System.out.println("USUARIO---------------------: " + us.getUsername());
            System.out.println("USUARIO---------------------: " + us.getPassword());
            if (usuario != null && encoderService.isValidPassword(us.getPassword(), usuario.getPassword())) {
                System.out.println("ENTRE ----------------------------------");
                UsernamePasswordAuthenticationToken usuario2 = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
                System.out.println("USUARIO 2 ------------------------------------" + usuario2);
                try {
                    Authentication authentication = authenticationManager.authenticate(usuario2);
                    if (authentication.isAuthenticated()) {
                        // autenticación exitosa
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("Autenticación exitosa");
                        return "redirect:/admin/users/";
                    } else {
                        System.out.println("Autenticación fallida");
                        model.addAttribute("errorMessage", "Error de inicio de sesión");
                        return "HomeLoginPage";
                    }
                } catch (AuthenticationException e) {
                    System.out.println("Autenticación fallida");
                    model.addAttribute("errorMessage", "Error de inicio de sesión");
                    return "HomeLoginPage";
                }
            }
        }
        model.addAttribute("errorMessage", "Error de inicio de sesión");
        return "HomeLoginPage";
    }

    @RequestMapping("/login")
    public String showLoginPage(Model model) {
        /*model.addAttribute("_csrf", new CsrfToken());*/
        model.addAttribute("usuario", new Usuario());
        return "HomeLoginPage";
    }

    @GetMapping("/register")
    public String CreateUser(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarioDto", new UsuarioDto());
        model.addAttribute("roles", adminService.RolefindAll());
        System.out.println("------------------ LLAMADA ----------------");
        return "AdminCreateUsersFirstTime";
    }

    @PostMapping("/register")
    public String SaveUser(@ModelAttribute("usuarioDto") UsuarioDto usuarioDto, Model model) {
        System.out.println("------------------ ENVIO ----------------");
        if (!usuarioDto.getPassword().equals(usuarioDto.getPassword2())) {
            System.out.println("------------------ NO ENVIO ----------------");
            model.addAttribute("errM", "Las contraseñas no coinciden");
            model.addAttribute("usuarioDto", usuarioDto);
            model.addAttribute("roles", adminService.RolefindAll());
            return "AdminCreateUsersFirstTime";
        } else {
            System.out.println("------------------ EXITOSO ----------------");
            Usuario usuario = new Usuario();
            usuario.setUsername(usuarioDto.getUsername());
            usuario.setPassword(encoderService.encode(usuarioDto.getPassword()));
            usuario.setRole(usuarioDto.getRole());
            System.out.println("-------------------------------> USUARIO: " + usuario);
            adminService.saveUser(usuario);
            return "redirect:/admin/users/";
        }
    }
}
