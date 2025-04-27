package com.pragma.foodcourt.security.infrastructure.output.jpa.adapter;

import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IFindUserPersistencePort;
import com.pragma.foodcourt.security.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.foodcourt.security.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.foodcourt.security.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindUserJpaAdapter implements IFindUserPersistencePort {

    private final IUserRepository userRepository;

    @Override
    public User findUserByIdentity(int userIdentityType, String userIdentityNumber) {

        UserEntity userEntity = userRepository.findByUserIdentityTypeAndUserIdentityNumber(userIdentityType, userIdentityNumber)
                .orElse(null);

        return IUserEntityMapper.INSTANCE.toUserModel(userEntity);
    }
}
