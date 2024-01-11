package com.challenge.model.dao;

import com.challenge.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombres(String nombres);
}
