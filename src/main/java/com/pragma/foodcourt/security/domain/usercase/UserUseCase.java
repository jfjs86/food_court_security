package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.api.IUserServicePort;
import com.pragma.foodcourt.security.domain.enums.ProfileEnum;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.model.Profile;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        user.setUserActive(true);
        return this.userPersistencePort.createUser(user);
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
