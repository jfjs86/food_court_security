package com.pragma.foodcourt.security.application.handler.impl;

import com.pragma.foodcourt.security.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.security.application.handler.IFindUserHandler;
import com.pragma.foodcourt.security.application.mapper.out.IUserResponseMapper;
import com.pragma.foodcourt.security.domain.api.IFindUserServicePort;
import com.pragma.foodcourt.security.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FindUserHandler implements IFindUserHandler {

    private final IFindUserServicePort findUserServicePort;

    @Override
    public UserResponseDto findUserByIdentity(int userIdentityType, String userIdentityNumber) {
        User user = findUserServicePort.findUserByIdentity(userIdentityType,userIdentityNumber);
        return IUserResponseMapper.INSTANCE.toUserDto(user);
    }
}
