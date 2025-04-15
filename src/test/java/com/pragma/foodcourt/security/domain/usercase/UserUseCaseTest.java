package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.enums.PermissionEnum;
import com.pragma.foodcourt.security.domain.enums.ProfileEnum;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.model.Permission;
import com.pragma.foodcourt.security.domain.model.Profile;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserIdentityType(IdentityTypeEnum.CEDULA);
        user.setUserIdentityNumber("5200433");
        user.setUserFirstName("Pedro");
        user.setUserSecondName("Pablo");
        user.setUserFirstLastName("Pérez");
        user.setUserSecondLastName("Pereira");
        user.setUserPhone("+573115208877");
        user.setUserBirthdate(LocalDate.now().minusYears(30));
        user.setUserEmail("pperez@mail.com");
        user.setUserPassword("contraseña123#$%");
        user.setUserActive(true);

        Set<Permission> permissions = new HashSet<>();
        Permission permission = new Permission();
        permission.setPermissionId(PermissionEnum.CREATE_USER.getId());
        permission.setNamePermission(PermissionEnum.CREATE_USER.getName());
        permissions.add(permission);

        Set<Profile> profiles = new HashSet<>();
        Profile profile = new Profile();
        profile.setProfileId(ProfileEnum.PROPIETARIO.getId());
        profile.setProfileName(ProfileEnum.PROPIETARIO.getName());
        profile.setProfilePermissions(permissions);

        profiles.add(profile);
        user.setUserProfiles(profiles);

    }

    @Test
    void createUser_valid() {
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);

        User createdUser = userUseCase.createOwnerUser(user);

        assertNotNull(createdUser);
        assertEquals(user, createdUser);
        assertTrue(createdUser.isUserActive());
        assertNotNull(createdUser.getUserProfiles());
        assertEquals(user.getUserProfiles(), createdUser.getUserProfiles());

        if (user.getUserProfiles() != null) {
            assertEquals(user.getUserProfiles().size(), createdUser.getUserProfiles().size());
            assertTrue(createdUser.getUserProfiles().containsAll(user.getUserProfiles()));
        } else {
            assertNull(createdUser.getUserProfiles());
        }

        if (user.getUserProfiles() != null && createdUser.getUserProfiles() != null) {
            for (Profile expectedProfile : user.getUserProfiles()) {
                createdUser.getUserProfiles().stream()
                        .filter(actualProfile -> actualProfile.getProfileId() == expectedProfile.getProfileId())
                        .findFirst()
                        .ifPresent(actualProfile -> {
                            assertEquals(expectedProfile.getProfileName(), actualProfile.getProfileName());
                            if (expectedProfile.getProfilePermissions() != null) {
                                assertEquals(expectedProfile.getProfilePermissions().size(), actualProfile.getProfilePermissions().size());
                                assertTrue(actualProfile.getProfilePermissions().containsAll(expectedProfile.getProfilePermissions()));
                            } else {
                                assertNull(actualProfile.getProfilePermissions());
                            }
                        });
            }
        }
    }

    @Test
    void createUser_invalid_email_format_without_at_symbol(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserEmail("pperemail.com");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid email format", exception.getMessage());
    }

    @Test
    void createUser_invalid_email_format_without_mail_domain(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserEmail("ppere@mail");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid email format", exception.getMessage());
    }

    @Test
    void createUser_invalid_user_age(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserBirthdate(LocalDate.now().minusYears(15));
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: User must be at least 18 years old", exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_character(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserPhone("#573102334312");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid phone number format or length (max 13 digits with optional '+')", exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_lenght(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserPhone("+573102334312222");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid phone number format or length (max 13 digits with optional '+')", exception.getMessage());
    }

}
