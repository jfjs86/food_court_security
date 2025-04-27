package com.pragma.foodcourt.security.application.handler;

import com.pragma.foodcourt.security.application.dto.out.UserResponseDto;

public interface IFindUserHandler {

    UserResponseDto findUserByIdentity(int userIdentityType, String userIdentityNumber);

}
