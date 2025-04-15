package com.pragma.foodcourt.security.infrastructure.output.jpa.mapper;

import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserEntityMapper {

    IUserEntityMapper INSTANCE = Mappers.getMapper(IUserEntityMapper.class);

    @Mapping(source = "userIdentityType", target = "userIdentityType")
    User toUserModel(UserEntity userEntity);

    @Mapping(source = "userIdentityType", target = "userIdentityType")
    UserEntity toUserEntity(User user);

    default int fromIdentityTypeEnumToInt(IdentityTypeEnum identityType) {
        return identityType == null ? null : identityType.getId();
    }

    default IdentityTypeEnum fromIntToIdentityTypeEnum(Integer id) {
        return id == null ? null : IdentityTypeEnum.fromId(id);
    }
}
