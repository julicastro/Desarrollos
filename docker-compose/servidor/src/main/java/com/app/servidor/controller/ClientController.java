package com.app.servidor.controller;


import com.app.servidor.persistence.entity.Client;
import com.app.servidor.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> dockerGtAllClients(){
        List<Client> clients = clientService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    public ResponseEntity<String> postEndpoint(){
        return ResponseEntity.ok().body("Post ejecutado correctamente");
    }



}
