package com.security.api.authorizationserver.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login"; // jsp o html
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout"; // spring security lo crea x defecto
        // este get solo redirige a la vista.
        // le doy clic al okay y pasa al postmapping de logout
    }

    @PostMapping("/logout")
    public String logoutOk(HttpSecurity http) throws Exception {
        // este hace el proceso de logout verdadero
        http.logout(logoutConfig -> {
            logoutConfig.logoutSuccessUrl("login?logout") // url de logout satisfactorio
                    .deleteCookies("JSESSIONID") // elimina este tipo de cookie q contiene el ID de sesion
                    // el auth server maneja sesion x más q nosotros no manejemos estados.
                    .clearAuthentication(true) // borra objeto Authentication dentro del SecurityContext
                    .invalidateHttpSession(true);
        });
        return "login?logout";
    }

}
