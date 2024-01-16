package com.cursos.api.springsecuritycourse.persistence.entity;

import com.cursos.api.springsecuritycourse.persistence.util.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "\"user\"") // user es una palabra reservada en bds
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* necesito convertir los permisos del Role en una Collection de
           objetos q implementen GrantedAuthority. 3 clases la implemenan.
           SimpleGrantedAuthority */
        if(role == null) return null;
        if(role.getPermission() == null) return null;
        role.getPermission().stream()
                .map(each -> each.name())
                .map(each -> new SimpleGrantedAuthority(each))
//                .map(each -> {
//                    String permission = each.name();
//                    return new SimpleGrantedAuthority(permission);
//                })
                .collect(Collectors.toList());
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // no está expirada
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // no está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // no está expirada
    }

    @Override
    public boolean isEnabled() {
        return true; // está habilitada
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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
}
