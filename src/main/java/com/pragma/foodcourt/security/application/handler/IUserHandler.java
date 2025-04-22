package com.pragma.foodcourt.security.application.handler;

import com.pragma.foodcourt.security.application.dto.UserRequestDto;
import com.pragma.foodcourt.security.application.dto.UserResponseDto;

public interface IUserHandler {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserByTypeAndNumberIdentity(int identityType, String identityNumber);

}
