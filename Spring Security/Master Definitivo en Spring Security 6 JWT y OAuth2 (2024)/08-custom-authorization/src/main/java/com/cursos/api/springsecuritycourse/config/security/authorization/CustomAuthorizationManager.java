package com.cursos.api.springsecuritycourse.config.security.authorization;

import com.cursos.api.springsecuritycourse.exception.ObjectNotFoundException;
import com.cursos.api.springsecuritycourse.persistence.entity.security.Operation;
import com.cursos.api.springsecuritycourse.persistence.entity.security.User;
import com.cursos.api.springsecuritycourse.persistence.repository.security.OperationRepository;
import com.cursos.api.springsecuritycourse.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    // el verify llama al check

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserService userService;

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
        if (isPublic){
            return new AuthorizationDecision(true);
        }
        boolean isGranted = isGranted(url, httpMethod, authentication.get());
        return new AuthorizationDecision(isGranted);

    }

    private boolean isGranted(String url, String httpMethod, Authentication authentication) {
        if (authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)){
            // si es nulo es xq no se autenticó
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }
        List<Operation> operations = obtainedOperations(authentication);
        boolean isGranted = operations.stream().anyMatch(getOperationPredicate(url, httpMethod));
        System.out.println(isGranted);
        return isGranted;
    }

    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();
            // regex = '/[0-9]* de la fila de la base de datos
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url); // patron debe hacer match con url
            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    private List<Operation> obtainedOperations(Authentication authentication) {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        User user = userService.findOneByUsername(username).orElseThrow(
                () -> new ObjectNotFoundException("User not found. Username: " + username));
        return user.getRole().getPermissions().stream()
                .map(grantedPermission -> grantedPermission.getOperation()) // mapea
                .collect(Collectors.toList()); // convierte en lista
    }

    private boolean isPublic(String url, String httpMethod) {
        // obtenemos los endpoints publicos de base de datos.
        List<Operation> publicAccessEndpoints = operationRepository
                .findByPublicAccess();
        boolean isPublic = publicAccessEndpoints.stream().anyMatch(getOperationPredicate(url, httpMethod));
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
