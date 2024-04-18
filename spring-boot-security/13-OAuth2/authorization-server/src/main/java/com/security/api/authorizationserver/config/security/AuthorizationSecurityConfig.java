package com.security.api.authorizationserver.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@EnableWebSecurity
public class AuthorizationSecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http); // inyecta config
        // OAuth2 -> open authorization. OpenID -> es para autenticación. OpenId está x encima. OpenIDContect junta OAuth2 y OpenId
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());
        http.exceptionHandling(exceptionConfig -> {
            exceptionConfig.authenticationEntryPoint( new LoginUrlAuthenticationEntryPoint("/login")); // este es un endpoint de un controller
        });
        // config como se van a generar los tokens.
        http.oauth2ResourceServer(oauthResourceServerConfig -> {
            oauthResourceServerConfig.jwt(Customizer.withDefaults());
        });
        return http.build();
    }

    /*
     * cuando haga peticiones la 1 es la primera q tome la petición. la 1 al no estar logeado va a redirigir a la url /login. y
     * ahi entra en juego la siguiente securityFilterChain con un form para hacer login. debemos hacer publico el endpoint /login
     */

    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authConfig -> {
            authConfig.requestMatchers("/login").permitAll(); // indicamos q /login es publica
            authConfig.anyRequest().authenticated(); // para el resto debemos estar autenticados
        });
        http.formLogin(formLoginConfig -> formLoginConfig.loginPage("/login").permitAll() ); // es un login x defecto

        return http.build();
    }

    /* cuando quiera hacer el primer paso para obtener en mi flujo de OAuth2 voy a soliciar un Auth Code. el endpoint para esto
    * es el endpoint /authorize. va a aentrar al filtro 1 y si no estoy logeado tira excepcion y me manda al filtro 2 q es el
    * unico q puede dar solucion al /login. me redirige al form html. va a volver a entrar al filtro 1. busca el obheto Authentication,
    * lo va a encontrar y x ende no lanza excepcion y me devuelve el Auth Code */


}
