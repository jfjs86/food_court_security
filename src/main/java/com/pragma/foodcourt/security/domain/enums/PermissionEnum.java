package com.pragma.foodcourt.security.domain.enums;

public enum PermissionEnum {

    CREATE_USER("create_user");

    private final String name;

    PermissionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
