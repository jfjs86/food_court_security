package com.pragma.foodcourt.security.application.handler.impl;

import com.pragma.foodcourt.security.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.security.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.security.application.handler.IOwnerUserHandler;
import com.pragma.foodcourt.security.application.mapper.in.IUserRequestMapper;
import com.pragma.foodcourt.security.application.mapper.out.IUserResponseMapper;
import com.pragma.foodcourt.security.domain.api.IOwnerUserServicePort;
import com.pragma.foodcourt.security.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerUserHandler implements IOwnerUserHandler {

    private final IOwnerUserServicePort ownerUserServicePort;

    @Override
    public UserResponseDto createOwnerUser(UserRequestDto userRequestDto) {
        User ownerUser = ownerUserServicePort.createOwnerUser(IUserRequestMapper.INSTANCE.toUserModel(userRequestDto));
        return IUserResponseMapper.INSTANCE.toUserDto(ownerUser);
    }
}
