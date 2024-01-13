package com.cursos.api.springsecuritycourse.controller;

import com.cursos.api.springsecuritycourse.persistence.entity.Product;
import com.cursos.api.springsecuritycourse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable){
        Page<Product> productsPage = productService.findAll(pageable);
        if(productsPage.hasContent()){
            return ResponseEntity.ok(productsPage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOne(@PathVariable Long productId){
        Optional<Product> product = productService.findOneById(productId);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get()); // get para obtener el valor del optional
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }





}
