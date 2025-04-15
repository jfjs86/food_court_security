package com.pragma.foodcourt.security.infrastructure.output.jpa.adapter;

import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.security.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.foodcourt.security.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.foodcourt.security.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;

    @Override
    public User createOwnerUser(User user) {

        if(userRepository.findByUserIdentityTypeAndUserIdentityNumber(
                user.getUserIdentityType().getId(), user.getUserIdentityNumber()).isPresent()
        ){
            throw new InvalidUserException("Owner user already exists");
        }
        UserEntity ownerUserEntity = userRepository.save(IUserEntityMapper.INSTANCE.toUserEntity(user));
        return IUserEntityMapper.INSTANCE.toUserModel(ownerUserEntity);
    }
}
