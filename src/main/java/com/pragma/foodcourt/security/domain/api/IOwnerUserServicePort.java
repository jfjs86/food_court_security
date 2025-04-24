package com.pragma.foodcourt.security.domain.api;

import com.pragma.foodcourt.security.domain.model.User;

public interface IOwnerUserServicePort {

    User createOwnerUser(User user);

}
