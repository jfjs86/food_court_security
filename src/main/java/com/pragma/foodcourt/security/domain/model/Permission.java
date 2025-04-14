package com.pragma.foodcourt.security.domain.model;

import com.pragma.foodcourt.security.domain.enums.PermissionEnum;

public class Permission {

    private PermissionEnum permissionId;
    private String namePermission;

    public PermissionEnum getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(PermissionEnum permissionId) {
        this.permissionId = permissionId;
    }

    public String getNamePermission() {
        return namePermission;
    }

    public void setNamePermission(String namePermission) {
        this.namePermission = namePermission;
    }
}
