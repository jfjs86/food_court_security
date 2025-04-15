package com.pragma.foodcourt.security.domain.spi;

import com.pragma.foodcourt.security.domain.model.User;

public interface IUserPersistencePort {

    User createOwnerUser(User user);
}
