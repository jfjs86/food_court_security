package com.pragma.foodcourt.security.infrastructure.configuration;

import com.pragma.foodcourt.security.domain.api.IUserServicePort;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.security.domain.usercase.UserUseCase;
import com.pragma.foodcourt.security.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.pragma.foodcourt.security.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }

}
