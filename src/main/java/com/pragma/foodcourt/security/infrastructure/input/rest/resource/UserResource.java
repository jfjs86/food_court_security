package com.pragma.foodcourt.security.infrastructure.input.rest.resource;

public class UserResource {

    public static final String ROOT= RootResource.ROOT+"/user";
    public static final String CREATE_USER="/create-user";
    public static final String GET_USER_BY_TYPE_AND_NUMBER_IDENTITY = "/get-user-by-type-number-identity";

    private UserResource() {
    }
}
