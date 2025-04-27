package com.pragma.foodcourt.security.domain.enums;

import com.pragma.foodcourt.security.domain.exception.UserErrorMessage;

import java.util.Arrays;

public enum IdentityTypeEnum {

    CEDULA(1,"Cédula de Ciudadanía"),
    CEDULA_EXTRANJERIA(2,"Cédula de Extranjería"),
    PASAPORTE(3,"Pasaporte"),
    TARJETA_IDENTIDAD(4,"Tarjeta de Identidad"),
    NIT(5,"Número de Identificación Tributaria"),
    OTRO(6,"Otro");

    private final int id;
    private final String description;

    IdentityTypeEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static IdentityTypeEnum fromId(int id){
        return Arrays.stream(IdentityTypeEnum.values())
                .filter(identityType -> identityType.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UserErrorMessage.INVALID_USER_ID_TYPE));
    }

    public static IdentityTypeEnum fromName(String name){
        return Arrays.stream(IdentityTypeEnum.values())
                .filter(identityType -> identityType.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UserErrorMessage.INVALID_USER_ID_TYPE));

    }


}
