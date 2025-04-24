package com.pragma.foodcourt.security.domain.enums;

import com.pragma.foodcourt.security.domain.exception.UserErrorMessage;

import java.util.Arrays;

public enum ProfileEnum {

    ADMINISTRADOR(1,"Administrador"),
    PROPIETARIO(2,"Propietario"),
    EMPLEADO(3,"Empleado"),
    CLIENTE(4,"Cliente");

    private final int id;
    private final String name;

    ProfileEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProfileEnum fromId(int id) {
        return Arrays.stream(ProfileEnum.values())
                .filter(profile -> profile.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UserErrorMessage.PROFILE_INVALID_ID));
    }


    public static ProfileEnum fromName(String name){

        return Arrays.stream(ProfileEnum.values())
                .filter(profile -> profile.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UserErrorMessage.PROFILE_INVALID_NAME));

    }


}
