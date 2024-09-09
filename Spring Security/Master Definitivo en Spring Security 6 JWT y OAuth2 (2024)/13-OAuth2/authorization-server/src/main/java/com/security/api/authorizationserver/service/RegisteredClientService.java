package com.security.api.authorizationserver.service;

import com.security.api.authorizationserver.exception.ObjectNotFoundException;
import com.security.api.authorizationserver.mapper.ClientAppMapper;
import com.security.api.authorizationserver.persistence.entiy.security.ClientApp;
import com.security.api.authorizationserver.persistence.repository.security.ClientAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisteredClientService implements RegisteredClientRepository {

    @Autowired
    private ClientAppRepository clientAppRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        // nada xq los creo desde un data.sql. pero se puede crear
    }

    /* el registeredClient va a tener un id y clientId q son 2 atributos distintos pero con el mismo valor */

    @Override
    public RegisteredClient findById(String id) {
        ClientApp clientApp = clientAppRepository.findByClientId(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found"));
        return ClientAppMapper.toRegisteredClient(clientApp);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.findById(clientId);
    }

}
