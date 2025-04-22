package com.pragma.foodcourt.security.application.handler;

import com.pragma.foodcourt.security.application.dto.UserRequestDto;
import com.pragma.foodcourt.security.application.dto.UserResponseDto;
import com.pragma.foodcourt.security.application.mapper.IUserRequestMapper;
import com.pragma.foodcourt.security.application.mapper.IUserResponseMapper;
import com.pragma.foodcourt.security.domain.api.IUserServicePort;
import com.pragma.foodcourt.security.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandlerImpl implements IUserHandler{

    private final IUserServicePort userServicePort;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userServicePort.createUser(IUserRequestMapper.INSTANCE.toUserModel(userRequestDto));
        return IUserResponseMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public UserResponseDto getUserByTypeAndNumberIdentity(int identityType, String identityNumber) {
        User user = userServicePort.getUserByTypeAndNumberIdentity(identityType, identityNumber);
        return IUserResponseMapper.INSTANCE.toUserDto(user);
    }
}
