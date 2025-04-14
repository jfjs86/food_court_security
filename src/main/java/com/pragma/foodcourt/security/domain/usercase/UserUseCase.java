package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.api.IUserServicePort;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PHONE_REGEX = "^[+0-9]+$";
    private static final int MAX_PHONE_LENGTH = 13;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User createUser(User user) {
        List<String> errors = validateUser(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException("Invalid user data: " + String.join(", ", errors));
        }
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
            errors.add("Birthdate is required");
        } else if (!isAdult(user.getUserBirthdate())) {
            errors.add("User must be at least 18 years old");
        }
        if (isNullOrEmpty(user.getUserEmail()) || (!validateEmail(user.getUserEmail())) ) {
            errors.add("Invalid email format");
        }
        if (isNullOrEmpty(user.getPhone())) {
            errors.add("Phone number is required");
        } else if (!isValidPhone(user.getPhone())) {
            errors.add("Invalid phone number format or length (max " + MAX_PHONE_LENGTH + " digits with optional '+')");
        }
        if (isNullOrEmpty(user.getUserPassword())) {
            errors.add("Password is required");
        } else if (user.getUserPassword().length() < 8) {
            errors.add("Password must be at least 8 characters long");
        }

        return errors;
    }

    private boolean validateEmail(String email){

        if (email == null || email.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isAdult(LocalDate birthdate) {
        LocalDate today = LocalDate.now(java.time.Clock.systemDefaultZone());
        Period period = Period.between(birthdate, today);
        return period.getYears() >= 18;
    }

    private boolean isValidPhone(String phone) {
        if (isNullOrEmpty(phone)) {
            return false;
        }
        String trimmedPhone = phone.trim();
        return trimmedPhone.length() <= MAX_PHONE_LENGTH && Pattern.matches(PHONE_REGEX, trimmedPhone);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
