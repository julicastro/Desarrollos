package com.truthtrace.security.service;

import com.truthtrace.security.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
}
