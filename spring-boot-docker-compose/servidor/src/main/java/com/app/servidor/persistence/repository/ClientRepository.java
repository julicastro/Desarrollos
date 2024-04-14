package com.app.servidor.persistence.repository;

import com.app.servidor.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
