package com.challenge.service.interfaces;

import com.challenge.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> getAll();

    Cliente getOne(Long id);

    void save(Cliente cliente);

    List<Cliente> search(String searchTerm); /* conviene hacerlo en la vista */

    /*
        cambiados por un save()
        void insert(Cliente cliente);
        void update(Cliente cliente);
     */






}
