package com.pragma.foodcourt.security.application.mapper;

import com.pragma.foodcourt.security.application.dto.UserRequestDto;
import com.pragma.foodcourt.security.application.dto.UserResponseDto;
import com.pragma.foodcourt.security.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserResponseMapper {
    IUserResponseMapper INSTANCE = Mappers.getMapper(IUserResponseMapper.class);
    UserResponseDto toUserDto(User user);

}
