package com.security.api.authorizationserver.persistence.entiy.security;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientApp {

    // entidad para recuperar a la App Cliente desde bd
    // luego debo mapear el ClientApp con RegisteredClient

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId; // username del cliente

    private String clientSecret; // contraseña del cliente

    @ElementCollection(fetch = FetchType.EAGER) /* crea la tabla de 1 a N con esta @annotation con la nomenclatura a_b_c pero antes les concatena client_app al principio */
    private List<String> clientAuthenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorizationGrantTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> redirectUris; // a este le voy a mandar el Auth Code. O al cliente.

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> scopes;

    private int durationInMinutes;

    private boolean requiredProofKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public List<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public boolean isRequiredProofKey() {
        return requiredProofKey;
    }

    public void setRequiredProofKey(boolean requiredProofKey) {
        this.requiredProofKey = requiredProofKey;
    }
}
