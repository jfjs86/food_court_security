package com.pragma.foodcourt.security.domain.enums;

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
        for (IdentityTypeEnum identityTypeEnum : IdentityTypeEnum.values()){
            if(identityTypeEnum.id==id){
                return identityTypeEnum;
            }
        }

        throw new IllegalArgumentException("Invalid identity type id");
    }

    public static IdentityTypeEnum fromName(String name){
        for (IdentityTypeEnum identityTypeEnum : IdentityTypeEnum.values()){
            if(identityTypeEnum.name().equalsIgnoreCase(name)){
                return identityTypeEnum;
            }
        }

        throw new IllegalArgumentException("Invalid identity type id");
    }


}
