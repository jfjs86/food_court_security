package com.pragma.foodcourt.security.infrastructure.output.jpa.mapper;

import com.pragma.foodcourt.security.domain.model.Profile;
import com.pragma.foodcourt.security.infrastructure.output.jpa.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IProfileEntityMapper {

    IProfileEntityMapper INSTANCE = Mappers.getMapper(IProfileEntityMapper.class);

//    @Mapping(source = "userIdentityType", target = "userIdentityType")
    Profile toProfileModel(ProfileEntity profileEntity);
}
