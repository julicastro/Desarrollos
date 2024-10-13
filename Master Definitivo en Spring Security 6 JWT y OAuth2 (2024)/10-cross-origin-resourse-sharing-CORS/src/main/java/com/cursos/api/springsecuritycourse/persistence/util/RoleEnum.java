package com.cursos.api.springsecuritycourse.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {

    OWNER(Arrays.asList(
            RolePermissionEnum.ALL
    )),
    PRODUCTORA(Arrays.asList(
            /* Administrador */
            /* Usuario */
    )),
    TRANSPORTE(Arrays.asList(
            /* Administrador */
            /* transportista */
    )),
    RECEPTOR(Arrays.asList(
        /* USUARIO FINAL -> acceso a QR o clave, etc */
    ));

    private List<RolePermissionEnum> permissions;

    RoleEnum(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
