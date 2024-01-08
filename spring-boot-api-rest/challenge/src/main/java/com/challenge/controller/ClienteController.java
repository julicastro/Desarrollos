package com.challenge.controller;

import com.challenge.model.entity.Cliente;
import com.challenge.service.interfaces.IClienteService;
import com.challenge.util.ErrorFormater;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    private ResponseEntity<String> save(@Valid @RequestBody Cliente cliente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = ErrorFormater.formatError(bindingResult);
            return new ResponseEntity<>("Errores de validación: " + "\n" + errors, HttpStatus.BAD_REQUEST);
        }
        if (cliente.getId() == null || clienteService.getOne(cliente.getId()) != null) {
            this.clienteService.save(cliente);
            return new ResponseEntity<>("Cliente guardado con éxito" + cliente.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al guardar cliente", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/{nombre}")
    private List<Cliente> getClientesByNombre(@PathVariable("nombre") String nombre){
        return this.clienteService.findByNombres(nombre);
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") Long id) throws Exception {
        try {
            clienteService.delete(id);
            return new ResponseEntity<>("Cliente eliminado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}