package com.pragma.foodcourt.security.domain.spi;

import com.pragma.foodcourt.security.domain.model.User;

public interface IUserPersistencePort {

    User createUser(User user);

    String getPasswordEncoder(String plainPassword);

    boolean validateUserAlreadyExists(int identityType, String identityNumber);
}
