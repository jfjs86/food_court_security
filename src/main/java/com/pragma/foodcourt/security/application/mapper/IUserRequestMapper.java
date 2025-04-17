package com.pragma.foodcourt.security.application.mapper;

import com.pragma.foodcourt.security.application.dto.UserRequestDto;
import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.model.Profile;
import com.pragma.foodcourt.security.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface IUserRequestMapper {

    IUserRequestMapper INSTANCE = Mappers.getMapper(IUserRequestMapper.class);

    @Mapping(source = "userIdentityType", target = "userIdentityType")
    User toUserModel(UserRequestDto userRequestDto);

    default int fromIdentityTypeEnumToInt(IdentityTypeEnum identityType) {

        if(identityType == null || identityType.getId() ==0){
            return 0;
        }

        return  identityType.getId();
    }

    default IdentityTypeEnum fromIntToIdentityTypeEnum(Integer id) {
        return id == null ? null : IdentityTypeEnum.fromId(id);
    }

    default Set<Profile> map(Set<Integer> ids) {
        if (ids == null){
            return Collections.emptySet();
        }

        return ids.stream().map(id -> {
            Profile profile = new Profile();
            profile.setProfileId(id);
            return profile;
        }).collect(Collectors.toSet());
    }


}
