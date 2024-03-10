package com.cursos.api.springsecuritycourse.config.security.authorization;

import com.cursos.api.springsecuritycourse.persistence.entity.security.Operation;
import com.cursos.api.springsecuritycourse.persistence.repository.security.OperationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    // el verify llama al check

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication,
                                       RequestAuthorizationContext requestContext) {
        // requestContext -> saco peticion + url
        HttpServletRequest request = requestContext.getRequest();
        // URI -> no trae host y puerto. URL si
        // System.out.println(request.getRequestURI());
        String url = extractUrl(request);
        String httpMethod = request.getMethod();
        boolean isPublic = isPublic(url, httpMethod);


        return new AuthorizationDecision(isPublic);
    }

    private boolean isPublic(String url, String httpMethod) {
        // obtenemos los endpoints publicos de base de datos.
        List<Operation> publicAccessEndpoints = operationRepository
                .findByPublicAccess();
        boolean isPublic = publicAccessEndpoints.stream().anyMatch(operation -> {
            String basePath = operation.getModule().getBasePath();
            // regex = '/[0-9]* de la fila de la base de datos
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url); // patron debe hacer match con url

           return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        });
        System.out.println("IS PUBLIC " + isPublic);
        return isPublic;
    }

    private String extractUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        // contextPath = /api/v1 tal como dice el properties.
        String url = request.getRequestURI();
        url = url.replace(contextPath, ""); // sacamos el contextPath
        return url;
    }


}
