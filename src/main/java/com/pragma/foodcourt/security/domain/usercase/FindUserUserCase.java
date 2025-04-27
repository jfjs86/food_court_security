package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.api.IFindUserServicePort;
import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.exception.UserErrorMessage;
import com.pragma.foodcourt.security.domain.exception.UserNotFoundException;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IFindUserPersistencePort;
import com.pragma.foodcourt.security.domain.util.DomainUtils;

import java.util.ArrayList;
import java.util.List;

public class FindUserUserCase implements IFindUserServicePort {

    private final IFindUserPersistencePort findUserPersistencePort;

    public FindUserUserCase(IFindUserPersistencePort findUserPersistencePort) {
        this.findUserPersistencePort = findUserPersistencePort;
    }


    @Override
    public User findUserByIdentity(int userIdentityType, String userIdentityNumber) {

        List<String> errors = validateIdentity(userIdentityType, userIdentityNumber);

        if (!errors.isEmpty()) {
            throw new InvalidUserException(UserErrorMessage.INVALID_USER_DATA
                    + String.join(", ", errors));
        }

        User foundUser = findUserPersistencePort.findUserByIdentity(userIdentityType, userIdentityNumber);

        if(foundUser == null){
            throw new UserNotFoundException(String.format(UserErrorMessage.UER_NOT_FOUND, userIdentityType, userIdentityNumber));
        }

        return foundUser;
    }

    private List<String> validateIdentity(Integer userIdentityType, String userIdentityNumber){
        List<String> errors = new ArrayList<>();

        if(userIdentityType == null || userIdentityType < 0){
            errors.add(UserErrorMessage.INVALID_USER_ID_TYPE);
        }

        if(IdentityTypeEnum.fromId(userIdentityType) == null){
            errors.add(UserErrorMessage.INVALID_USER_ID_TYPE);
        }

        if(DomainUtils.isNullOrEmpty(userIdentityNumber)){
            errors.add(UserErrorMessage.INVALID_USER_ID_NUMBER);
        }

        return errors;
    }
}
