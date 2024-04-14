package com.cursos.api.springsecuritycourse.persistence.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /* EAGER -> obtiene todos de una
    * LAZY -> default para colecciones */

    @JsonIgnore // al generar json ignora este atributo para q no sea infinito
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER) // en grantedPermission debe tener un atributo role
    private List<GrantedPermission> permissions;

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

    public List<GrantedPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<GrantedPermission> permissions) {
        this.permissions = permissions;
    }
}
