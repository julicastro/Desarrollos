package com.security.api.authorizationserver.persistence.repository.security;

import com.security.api.authorizationserver.persistence.entiy.security.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientAppRepository extends JpaRepository<ClientApp, Long> {

    Optional<ClientApp> findByClientId(String clientId);

}
