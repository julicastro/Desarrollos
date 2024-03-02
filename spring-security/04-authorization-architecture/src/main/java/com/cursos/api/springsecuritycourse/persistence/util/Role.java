package com.cursos.api.springsecuritycourse.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum Role {

    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_PRODUCT,
            RolePermission.CREATE_ONE_PRODUCT,
            RolePermission.UPDATED_ONE_PRODUCT,
            RolePermission.DISABLED_ONE_PRODUCT,

            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_ONE_CATEGORY,
            RolePermission.CREATE_ONE_CATEGORY,
            RolePermission.UPDATED_ONE_CATEGORY,
            RolePermission.DISABLED_ONE_CATEGORY,

            RolePermission.READ_MY_PROFILE
    )),
    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_PRODUCT,
            RolePermission.DISABLED_ONE_PRODUCT,

            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_ONE_CATEGORY,
            RolePermission.DISABLED_ONE_CATEGORY,

            RolePermission.READ_MY_PROFILE
    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermission.READ_MY_PROFILE
    ));

    private List<RolePermission> permission;

    Role(List<RolePermission> permissions) {
        this.permission = permissions;  // Corregido aquí
    }

    public List<RolePermission> getPermission() {
        return permission;
    }

    public void setPermission(List<RolePermission> permission) {
        this.permission = permission;
    }
}