package com.app.servidor.service;

import com.app.servidor.persistence.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

}
