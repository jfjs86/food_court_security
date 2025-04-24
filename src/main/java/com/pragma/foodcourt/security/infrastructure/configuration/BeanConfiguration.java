package com.pragma.foodcourt.security.infrastructure.configuration;

import com.pragma.foodcourt.security.domain.api.IOwnerUserServicePort;
import com.pragma.foodcourt.security.domain.spi.IOwnerUserPersistencePort;
import com.pragma.foodcourt.security.domain.usercase.OwnerUserUseCase;
import com.pragma.foodcourt.security.infrastructure.output.jpa.adapter.OwnerUserJpaAdapter;
import com.pragma.foodcourt.security.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IOwnerUserPersistencePort ownerUserPersistencePort(){
        return new OwnerUserJpaAdapter(userRepository, bCryptPasswordEncoder());
    }

    @Bean
    public IOwnerUserServicePort ownerUserServicePort(){
        return new OwnerUserUseCase(ownerUserPersistencePort());
    }


}
