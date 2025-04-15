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
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);

        User createdUser = userUseCase.createUser(user);

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
    void createUser_no_first_name(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserFirstName(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: First name is required", exception.getMessage());
    }

    @Test
    void createUser_no_first_lastname(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserFirstLastName(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: First last name is required", exception.getMessage());
    }

    @Test
    void createUser_no_identity_type(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserIdentityType(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: Identity type is required", exception.getMessage());
    }

    @Test
    void createUser_no_identity_number(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserIdentityNumber(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: Identity number is required", exception.getMessage());
    }

    @Test
    void createUser_no_birthday(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserBirthdate(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: Birthday is required", exception.getMessage());
    }

    @Test
    void createUser_no_phone(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserPhone(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: Phone number is required", exception.getMessage());
    }

    @Test
    void createUser_no_password(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserPassword(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: Password is required", exception.getMessage());
    }

    @Test
    void createUser_password_invalid_lenght(){
        when(userPersistencePort.createUser(any(User.class))).thenReturn(user);
        user.setUserPassword("co12#@");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createUser(user));
        assertEquals("Invalid user data: Password must be at least 8 characters long", exception.getMessage());
    }

}
