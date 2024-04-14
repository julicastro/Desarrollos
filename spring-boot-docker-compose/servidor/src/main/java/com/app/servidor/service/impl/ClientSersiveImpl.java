package com.app.servidor.service.impl;

import com.app.servidor.persistence.entity.Client;
import com.app.servidor.persistence.repository.ClientRepository;
import com.app.servidor.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientSersiveImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

}
