package com.app.cliente.controller;

import java.net.http.HttpHeaders;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientRestController {

    @GetMapping
    public String getClientsFromServer() {

        return null;
    }

}
