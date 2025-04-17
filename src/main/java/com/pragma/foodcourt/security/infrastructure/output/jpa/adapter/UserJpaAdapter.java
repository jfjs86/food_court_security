package com.pragma.foodcourt.security.infrastructure.output.jpa.adapter;

import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;
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

        UserEntity userEntity = userRepository.save(IUserEntityMapper.INSTANCE.toUserEntity(user));
        return IUserEntityMapper.INSTANCE.toUserModel(userEntity);
    }

    @Override
    public String getPasswordEncoder(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public boolean validateUserAlreadyExists(int identityType, String identityNumber) {
        return userRepository.findByUserIdentityTypeAndUserIdentityNumber(
                identityType, identityNumber
        ).isPresent();
    }


}
