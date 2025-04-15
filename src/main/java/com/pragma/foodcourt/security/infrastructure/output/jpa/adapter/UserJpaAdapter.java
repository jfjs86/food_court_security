package com.pragma.foodcourt.security.infrastructure.output.jpa.adapter;

import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.security.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.foodcourt.security.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.foodcourt.security.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.foodcourt.security.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        if(userRepository.findByUserIdentityTypeAndUserIdentityNumber(
                user.getUserIdentityType().getId(), user.getUserIdentityNumber()).isPresent()
        ){
            throw new UserAlreadyExistsException("Owner user already exists");
        }

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        UserEntity ownerUserEntity = userRepository.save(IUserEntityMapper.INSTANCE.toUserEntity(user));
        return IUserEntityMapper.INSTANCE.toUserModel(ownerUserEntity);
    }
}
