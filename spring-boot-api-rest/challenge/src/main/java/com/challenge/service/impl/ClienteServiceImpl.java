package com.challenge.service.impl;

import com.challenge.model.dao.IClienteDao;
import com.challenge.model.entity.Cliente;
import com.challenge.service.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    public List<Cliente> getAll() {
        return clienteDao.findAll();
    }

    @Override
    public Cliente getOne(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    public List<Cliente> findByNombres(String nombres) {
        return clienteDao.findByNombres(nombres);
    }

    @Override
    public void delete(Long id){
        clienteDao.deleteById(id);
    }


}
