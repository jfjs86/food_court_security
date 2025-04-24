package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.api.IOwnerUserServicePort;
import com.pragma.foodcourt.security.domain.enums.ProfileEnum;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.exception.UserErrorMessage;
import com.pragma.foodcourt.security.domain.model.Profile;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IOwnerUserPersistencePort;
import com.pragma.foodcourt.security.domain.util.DomainUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OwnerUserUseCase implements IOwnerUserServicePort {

    private final IOwnerUserPersistencePort ownerUserPersistencePort;
    private static final int MINIMUM_AGE =18;
    private static final int MAX_PHONE_LENGTH = 13;

    public OwnerUserUseCase(IOwnerUserPersistencePort ownerUserPersistencePort) {
        this.ownerUserPersistencePort = ownerUserPersistencePort;
    }

    @Override
    public User createOwnerUser(User user) {

        List<String> errors = validateUser(user);

        if (!errors.isEmpty()) {
            throw new InvalidUserException(UserErrorMessage.INVALID_USER_DATA
                    + String.join(", ", errors));
        }

        assignOwnerProfile(user);

        user.setUserActive(true);
        user.setUserPassword(ownerUserPersistencePort.getPasswordEncoder(user.getUserPassword()));
        return this.ownerUserPersistencePort.createOwnerUser(user);
    }

    private List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        if (user == null) {
            return List.of(UserErrorMessage.USER_DATA_NULL);
        }

        if (user.getUserIdentityType() == null) {
            errors.add(UserErrorMessage.INVALID_USER_ID_TYPE);
        }

        if (DomainUtils.isNullOrEmpty(user.getUserIdentityNumber())) {
            errors.add(UserErrorMessage.INVALID_USER_ID_NUMBER);
        }

        if (!DomainUtils.isAdult(user.getUserBirthdate(), MINIMUM_AGE)) {
            errors.add(String.format(UserErrorMessage.USER_NOT_ADULT, MINIMUM_AGE));
        }

        String email = user.getUserEmail();
        if (DomainUtils.isNullOrEmpty(email) || !DomainUtils.validateEmail(email)) {
            errors.add(UserErrorMessage.INVALID_EMAIL_FORMAT);
        }

        if (!DomainUtils.isValidPhone(user.getUserPhone(), MAX_PHONE_LENGTH)) {
            errors.add(String.format(UserErrorMessage.INVALID_PHONE_FORMAT, MAX_PHONE_LENGTH));
        }

        return errors;
    }

    private void assignOwnerProfile(User user) {

        if(user == null){
            throw new InvalidUserException(UserErrorMessage.PROFILE_ASSIGN_EXCEPTION);
        }
        
        Profile ownerProfile = new Profile();
        ownerProfile.setProfileId(ProfileEnum.PROPIETARIO.getId());
        ownerProfile.setProfileName(ProfileEnum.PROPIETARIO.getName());

        user.setUserProfiles(Set.of(ownerProfile));
    }

}
