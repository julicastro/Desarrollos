package com.app.cliente.controller;

import com.app.cliente.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class ClientRestController {

    @Autowired
    private RestTemplateConfig restConfig;


    @GetMapping
    public String getClientsFromServer() throws URISyntaxException {
        String url = "http://localhost:6060/api/v1/client";
        // Crear la solicitud GET con las cabeceras configuradas
        RequestEntity<?> requestEntity = new RequestEntity<>(restConfig.headersWithOrigin(), HttpMethod.GET, new URI(url));
        // Crear un objeto RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // Hacer la solicitud GET y obtener la respuesta como un String
        String response = restTemplate.exchange(requestEntity, String.class).getBody();
        // Retornar la respuesta
        return response;
    }






}
