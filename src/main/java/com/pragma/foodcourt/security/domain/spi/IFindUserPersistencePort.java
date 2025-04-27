package com.pragma.foodcourt.security.domain.spi;

import com.pragma.foodcourt.security.domain.model.User;

public interface IFindUserPersistencePort {

    User findUserByIdentity(int userIdentityType, String userIdentityNumber);

}
