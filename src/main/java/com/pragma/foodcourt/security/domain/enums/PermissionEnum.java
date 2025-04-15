package com.pragma.foodcourt.security.domain.enums;

public enum PermissionEnum {

    CREATE_USER(1,"create_user");

    private final int id;
    private final String name;

    PermissionEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static PermissionEnum fromId(int id){
        for(PermissionEnum permissionEnum : PermissionEnum.values()){
            if(id == permissionEnum.id){
                return permissionEnum;
            }
        }

        throw new IllegalArgumentException("Invalid permission id");
    }

    public static PermissionEnum fromName(String name){
        for(PermissionEnum permissionEnum : PermissionEnum.values()){
            if(permissionEnum.name.equalsIgnoreCase(name)){
                return permissionEnum;
            }
        }

        throw new IllegalArgumentException("Invalid permission name");
    }
}
