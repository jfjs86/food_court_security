package com.pragma.foodcourt.security.domain.model;

import com.pragma.foodcourt.security.domain.enums.PermissionEnum;

public class Permission {

    private int permissionId;
    private String namePermission;

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getNamePermission() {
        return namePermission;
    }

    public void setNamePermission(String namePermission) {
        this.namePermission = namePermission;
    }
}
