package com.cursos.api.springsecuritycourse.config.security.filter;

import com.cursos.api.springsecuritycourse.exception.ObjectNotFoundException;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import com.cursos.api.springsecuritycourse.service.UserService;
import com.cursos.api.springsecuritycourse.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("ENTRO EN doFilterInternal DEL JwtAuthenticationFilter");
        // 1. obtener header http llamado Authorization
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // sigua con su trabajo
            return; // metodo void
        }
        // 2. obtener JWT desde el header
        String jwt = authorizationHeader.split(" ")[1]; // divide en base a un espacio vacio. da la posicion 1 q esquivale al token. la [0] es "Bearer"
        // 3. obtener subject/username desde el token. esto valida formato, fecha y formato
        String username = jwtService.extractUsername(jwt);
        // 4. setear objeto Authentication dentro de SecurityContextHolder.
        User user = userService.findOneByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username:  " + username));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        // 5. ejecutar resto de filtros.
        filterChain.doFilter(request, response);
        // no pongo return xq es la ultima linea del método
    }



}
