package com.challenge.service.interfaces;

import com.challenge.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> getAll();

    Cliente getOne(Long id);

    void save(Cliente cliente);

    List<Cliente> findByNombres(String nombres);

    void delete(Long id) throws Exception;
}
