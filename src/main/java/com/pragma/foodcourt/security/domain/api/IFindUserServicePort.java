package com.pragma.foodcourt.security.domain.api;

import com.pragma.foodcourt.security.domain.model.User;

public interface IFindUserServicePort {

    User findUserByIdentity(int userIdentityType, String userIdentityNumber);

}
