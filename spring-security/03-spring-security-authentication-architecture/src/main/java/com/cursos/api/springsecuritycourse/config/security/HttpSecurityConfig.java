package com.cursos.api.springsecuritycourse.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        SecurityFilterChain filterChain = http
                .csrf(csrfConfig -> csrfConfig.disable()) // proteccion contra csrf
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // tipo de app con o sin estado
                .authenticationProvider(daoAuthProvider) // estrategia
                .authorizeHttpRequests(authReqConfig -> {
                    authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST, "/**").permitAll();
                    // authReqConfig.requestMatchers(HttpMethod.POST, "/auth/validate").permitAll();
                    // tenemos q decirle q endpoints necesitan login
                    authReqConfig.anyRequest().authenticated(); // cualquier request q no sean las anteriores, necesitan del login.
                })
                .build(); // crea un DefaultSecurityFilterChain
        return filterChain;
    }




}
