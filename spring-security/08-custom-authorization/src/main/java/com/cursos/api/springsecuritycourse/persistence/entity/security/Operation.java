package com.cursos.api.springsecuritycourse.persistence.entity.security;

import jakarta.persistence.*;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // FIND_ALL_PRODUCTS, ETC

    private String path; // los endpoints tienen path base. /products x ejemplo.
    // luego está lo q le sigue q puede ser /product/product{Id}/disabled

    private String httpMathod;

    private boolean permitAll;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMathod() {
        return httpMathod;
    }

    public void setHttpMathod(String httpMathod) {
        this.httpMathod = httpMathod;
    }

    public boolean isPermitAll() {
        return permitAll;
    }

    public void setPermitAll(boolean permitAll) {
        this.permitAll = permitAll;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
