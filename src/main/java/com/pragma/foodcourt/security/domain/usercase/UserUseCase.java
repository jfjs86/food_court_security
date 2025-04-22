package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.api.IUserServicePort;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.exception.UserAlreadyExistsException;
import com.pragma.foodcourt.security.domain.exception.UserNotFoundException;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;

import java.util.ArrayList;
import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private static final int PASSWORD_LENGHT =8;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User createUser(User user) {
        List<String> errors = validateUser(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException("Invalid user data: " + String.join(", ", errors));
        }

        boolean userAlreadyExists = userPersistencePort.validateUserAlreadyExists(
                user.getUserIdentityType().getId(), user.getUserIdentityNumber()
        );

        if(userAlreadyExists){
            throw new UserAlreadyExistsException("User already exists");
        }

        user.setUserActive(true);
        user.setUserPassword(userPersistencePort.getPasswordEncoder(user.getUserPassword()));
        return this.userPersistencePort.createUser(user);
    }

    @Override
    public User getUserByTypeAndNumberIdentity(int identityType, String identityNumber) {

        if(userPersistencePort.getUserByTypeAndNumberIdentity(identityType, identityNumber)== null){
            throw new UserNotFoundException("User not found");
        }
        return userPersistencePort.getUserByTypeAndNumberIdentity(identityType, identityNumber);
    }

    private List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        if (user == null) {
            errors.add("User data cannot be null");
            return errors;
        }

        if (isNullOrEmpty(user.getUserFirstName())) {
            errors.add("First name is required");
        }
        if (isNullOrEmpty(user.getUserFirstLastName())) {
            errors.add("First last name is required");
        }

        if (user.getUserIdentityType() == null) {
            errors.add("Identity type is required");
        }

        if (isNullOrEmpty(user.getUserIdentityNumber())) {
            errors.add("Identity number is required");
        }

        if (user.getUserBirthdate() == null) {
            errors.add("Birthday is required");
        }

        if (isNullOrEmpty(user.getUserPhone())) {
            errors.add("Phone number is required");
        }

        if (isNullOrEmpty(user.getUserPassword())) {
            errors.add("Password is required");
        } else if (user.getUserPassword().length() < PASSWORD_LENGHT) {
            errors.add("Password must be at least "+PASSWORD_LENGHT+" characters long");
        }

        return errors;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
