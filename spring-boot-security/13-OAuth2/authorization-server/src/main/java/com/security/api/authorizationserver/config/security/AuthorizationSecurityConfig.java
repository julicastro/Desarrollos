package com.security.api.authorizationserver.config.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

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
            oauthResourceServerConfig.jwt(Customizer.withDefaults()); // los tokens son devueltos para usarlos x el resouceServer.
            // defecto se va a buscar una implemtanción de jwkSource la cual va a ser del tipo JWE
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

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
        /* para q nuestra api decodifique la api le pregunta al auth server como se va a decodificar. para eso necesitamos el AuthorizationServerSettings*/
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:9595/authorization-server") // esto es QUIEN GENERÓ LOS TOKEN. ISSUER = FIRMANTE
                // SE AGREGA UN CLAIM AL FIRMAR Q SE LLAMA ISSUER Y TE DICE QUIEN GENERÓ EL TOQUEN
                .build();
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }


}
