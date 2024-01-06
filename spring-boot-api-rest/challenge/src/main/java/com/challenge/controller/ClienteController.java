package com.challenge.controller;

import com.challenge.model.entity.Cliente;
import com.challenge.service.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping
    private List<Cliente> getClientes(){
        return this.clienteService.getAll();
    }

    @PostMapping
    private Cliente save(@RequestBody Cliente cliente){
        return this.clienteService.save(cliente);
    }

    @GetMapping(path = "/{nombre}")
    private List<Cliente> getClientesByNombre(@PathVariable("nombre") String nombre){
        return this.clienteService.findByNombres(nombre);
    }






}
