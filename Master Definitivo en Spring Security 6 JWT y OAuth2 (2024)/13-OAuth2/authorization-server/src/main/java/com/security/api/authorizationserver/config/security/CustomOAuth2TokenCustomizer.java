package com.security.api.authorizationserver.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        Authentication authentication = context.getPrincipal(); // no es el username. sino el authentication
        String tokenType = context.getTokenType().getValue(); // devuelve si es ID o Access Token
        if (tokenType.equals("access_token")) {
            List<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority) // funciona como un each
                    .collect(Collectors.toList());
            context.getClaims().claim("permissions", authorities);
        }
        /*
        if (tokenType.equals("id_token")) {
            podemos agregar avatar de usuario, fecha, email. ya q este es para q el cliente conozca la identidad completa del
            usuario. mientras q el access_token es el q se intercambia con el proyecto principal
        }
         */
    }
}
