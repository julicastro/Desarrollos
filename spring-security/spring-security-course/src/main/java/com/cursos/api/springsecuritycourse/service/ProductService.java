package com.cursos.api.springsecuritycourse.service;

import com.cursos.api.springsecuritycourse.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById(Long productId);
}
