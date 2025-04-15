package com.pragma.foodcourt.security.domain.api;

import com.pragma.foodcourt.security.domain.model.User;

public interface IUserServicePort {

    User createOwnerUser(User user);
}
