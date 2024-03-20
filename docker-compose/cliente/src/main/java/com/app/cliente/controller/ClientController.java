package com.app.cliente.controller;

import com.app.cliente.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/view")
public class ClientController {

    @Autowired
    private RestTemplateConfig restConfig;

    @RequestMapping(method = RequestMethod.GET)
    public String getClientsFromServerWithHTMLView(Model model) throws URISyntaxException {
        String url = "http://localhost:9090/api/v1/client";
        RequestEntity<?> requestEntity = new RequestEntity<>(restConfig.headersWithOrigin(), HttpMethod.GET, new URI(url));
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.exchange(requestEntity, String.class).getBody();
        model.addAttribute("response", response);
        return "views/clientView";
    }

}
