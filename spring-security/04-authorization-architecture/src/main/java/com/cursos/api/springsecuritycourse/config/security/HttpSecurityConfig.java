package com.cursos.api.springsecuritycourse.config.security;

import com.cursos.api.springsecuritycourse.config.security.filter.JwtAuthenticationFilter;
import com.cursos.api.springsecuritycourse.persistence.util.RolePermission;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        SecurityFilterChain filterChain = http
                .csrf(csrfConfig -> csrfConfig.disable()) // proteccion contra csrf
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // tipo de app con o sin estado
                .authenticationProvider(daoAuthProvider) // estrategia
                // agregamos nuestro filtro
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // agrego mi filtro antes de q se ejecute UsernamePasswordAuthenticationFilter
                .authorizeHttpRequests(authReqConfig -> {
                    buildRequestMatchers(authReqConfig);
                })
                .build(); // crea un DefaultSecurityFilterChain
        return filterChain;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        /*
        Authorizacion de endpoints de productos
        */
        authReqConfig.requestMatchers(HttpMethod.GET, "/products")
                .hasAuthority(RolePermission.READ_ALL_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
                .hasAuthority(RolePermission.READ_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/products")
                .hasAuthority(RolePermission.CREATE_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
                .hasAuthority(RolePermission.UPDATED_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
                .hasAuthority(RolePermission.DISABLED_ONE_PRODUCT.name());
        /*
        Authorizacion de endpoints de categories
        */
        authReqConfig.requestMatchers(HttpMethod.GET, "/categories")
                .hasAuthority(RolePermission.READ_ALL_CATEGORIES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/categories/{categoryId}")
                .hasAuthority(RolePermission.READ_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/categories")
                .hasAuthority(RolePermission.CREATE_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}")
                .hasAuthority(RolePermission.UPDATED_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}/disabled")
                .hasAuthority(RolePermission.DISABLED_ONE_CATEGORY.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/profile")
                .hasAuthority(RolePermission.READ_MY_PROFILE.name());
        /*
        Authorizacion de endpoints de públicos
        */
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        // tenemos q decirle q endpoints necesitan login
        authReqConfig.anyRequest().authenticated(); // cualquier request q no sean las anteriores, necesitan del login.
    }


}
