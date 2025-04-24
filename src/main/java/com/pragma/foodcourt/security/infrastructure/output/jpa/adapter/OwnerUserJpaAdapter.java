package com.pragma.foodcourt.security.infrastructure.output.jpa.adapter;

import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IOwnerUserPersistencePort;
import com.pragma.foodcourt.security.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.foodcourt.security.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.foodcourt.security.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class OwnerUserJpaAdapter implements IOwnerUserPersistencePort {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createOwnerUser(User user) {
        UserEntity userEntity = IUserEntityMapper.INSTANCE.toUserEntity(user);
        return IUserEntityMapper.INSTANCE.toUserModel(userRepository.save(userEntity));
    }

    @Override
    public String getPasswordEncoder(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
