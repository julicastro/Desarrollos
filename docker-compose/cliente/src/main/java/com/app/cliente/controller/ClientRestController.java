package com.app.cliente.controller;

import com.app.cliente.config.RestTemplateConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;


@RestController
public class ClientRestController {

    @Autowired
    private RestTemplateConfig restConfig;


    @GetMapping()
    @ResponseBody
    public String setOriginAndGetData() throws URISyntaxException {
        String url = "http://localhost:9090/api/v1/client";

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
