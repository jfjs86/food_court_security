package com.pragma.foodcourt.security.application.handler;

import com.pragma.foodcourt.security.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.security.application.dto.out.UserResponseDto;

public interface IOwnerUserHandler {

    UserResponseDto createOwnerUser(UserRequestDto userRequestDto);
}
