package com.pragma.foodcourt.security.application.mapper.out;

import com.pragma.foodcourt.security.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserResponseMapper {
    IUserResponseMapper INSTANCE = Mappers.getMapper(IUserResponseMapper.class);

    @Mapping(source = "userIdentityType", target = "userIdentityType")
    UserResponseDto toUserDto(User user);

    default int fromIdentityTypeEnumToInt(IdentityTypeEnum identityType) {

        if(identityType == null){
            return 0;
        }

        return identityType.getId();
    }
}
