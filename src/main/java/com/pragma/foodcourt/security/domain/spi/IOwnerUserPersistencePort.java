package com.pragma.foodcourt.security.domain.spi;

import com.pragma.foodcourt.security.domain.model.User;

public interface IOwnerUserPersistencePort {

    User createOwnerUser(User user);

    String getPasswordEncoder(String plainPassword);
}
