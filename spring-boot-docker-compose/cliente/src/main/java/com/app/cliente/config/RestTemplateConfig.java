package com.app.cliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;

@Configuration
public class RestTemplateConfig {

    @Bean
    public HttpHeaders headersWithOrigin() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Origin", "http://client-app");
        return headers;
    }

}
