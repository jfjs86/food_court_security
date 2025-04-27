package com.pragma.foodcourt.security.infrastructure.input.rest.resource;

public class UserResource {

    public static final String ROOT= RootResource.ROOT+"/user";
    public static final String CREATE_OWNER_USER="/create-owner-user";
    public static final String GET_USER_BY_IDENTITY="/get-user-by-identity";

    private UserResource() {
    }
}
