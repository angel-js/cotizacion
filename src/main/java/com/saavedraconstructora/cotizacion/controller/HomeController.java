package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.service.AuthenticationService;
import com.saavedraconstructora.cotizacion.service.UsuarioServicioImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private AuthenticationService authenticationService;

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @PostMapping("/login")
    public String authenticate(@RequestParam String username, @RequestParam String password,
                               HttpServletRequest request) throws ServletException {
        System.out.println("ENTRE -----------------");
        boolean authenticated = authenticationService.authenticate(username, password);
        if (authenticated) {
            System.out.println("Auntenticado -----------------");
            request.login(username, password);
            return "Home";
        } else {
            return "HomeLoginV";
        }
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/admin/cotizacion/home";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("VER EL FORMULARIO -----------------");
        return "HomeLoginV";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, null, auth);
        }
        return "redirect:/login?logout";
    }
/**
 * @PostMapping("/home")
 *     public String homeSubmit(@ModelAttribute User user, Model model) {
 *         User authenticatedUser = usuarioServicio.authenticate(user.getUsername(), user.getPassword());
 *         if (authenticatedUser != null) {
 *             model.addAttribute("name", authenticatedUser.getUsername());
 *             return "HomeSuccessView";
 *         } else {
 *             model.addAttribute("errorMessage", "Invalid username or password");
 *             return "HomeFormView";
 *         }
 *     }
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EncoderService encoderService;

    @PostMapping("/loginIn")
    public String login(Usuario us
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
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
        model.addAttribute("_csrf", new CsrfToken());
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

    **/
}
