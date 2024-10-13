package com.truthtrace.security.service;

import com.truthtrace.security.dto.SaveUser;
import com.truthtrace.security.persistence.entity.security.User;

import java.util.Optional;

public interface UserService {
    User registrOneCustomer(SaveUser newUser);

    Optional<User> findOneByUsername(String username);
}
