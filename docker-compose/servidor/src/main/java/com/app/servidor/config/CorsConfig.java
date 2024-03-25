package com.app.servidor.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] activeProfiles = environment.getActiveProfiles();

        if (activeProfiles.length > 0) {
            String activeProfile = activeProfiles[0];
            if ("docker".equals(activeProfile)) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8686/**", "http://localhost:8686/view", "http://client-app")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*");
            } else if ("dev".equals(activeProfile)) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5500")
                        .allowedMethods("GET")
                        .allowedHeaders("*");
            } else if ("local".equals(activeProfile)) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5500")
                        .allowedMethods("GET")
                        .allowedHeaders("*");
            }
        }
    }


}