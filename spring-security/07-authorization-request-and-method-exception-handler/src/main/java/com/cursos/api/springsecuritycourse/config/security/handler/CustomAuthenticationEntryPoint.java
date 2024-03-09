package com.cursos.api.springsecuritycourse.config.security.handler;

import com.cursos.api.springsecuritycourse.dto.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /* va a ser el punto de entrada a nuestra app */

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        /* se indica el tipo de respuesta y el estatus HTTP */
        /* AuthenticationEntryPoint x defecto devuelve texto plano xq esta pensado
        para devolver una vista
        */
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(authException.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("No se encontraron credenciales de autenticación. Por favor" +
                "inicie sesión para acceder a esta función");
        apiError.setTimestamp(LocalDateTime.now());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        /* para q no tenga problemas con un tipo de fecha especifico: jackson-datatype-jsr310 */

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        /* jjwt jackson librery */
        String apiErrorAsJson = objectMapper.writeValueAsString(apiError);

        response.getWriter().write(apiErrorAsJson);

        /* estas excepciones deben registrarse en el httpSecurityConfig
         */
    }

}
