package com.pragma.foodcourt.security.domain.exception;

public class UserErrorMessage {

    private UserErrorMessage() {}

    public static final String USER_DATA_NULL = "User data cannot be null";
    public static final String INVALID_USER_DATA ="Invalid user data: ";
    public static final String INVALID_USER_ID_TYPE ="User identity type invalid";
    public static final String INVALID_USER_ID_NUMBER ="User identity number cannot be null";
    public static final String USER_NOT_ADULT = "User must be at least %d years old";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    public static final String INVALID_PHONE_FORMAT = "Invalid phone number format or length (max %d digits with optional '+')";
    public static final String PROFILE_ASSIGN_EXCEPTION = "Error assigning profile to user";
    public static final String PROFILE_INVALID_ID = "Invalid profile id";
    public static final String PROFILE_INVALID_NAME = "Invalid profile name";

}
