package com.truthtrace.security.persistence.repository;

import com.truthtrace.security.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    Page<Product> findAll(Pageable pageable);

}
