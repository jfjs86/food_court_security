package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IOwnerUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OwnerUserUseCaseTest {

    @Mock
    private IOwnerUserPersistencePort userPersistencePort;

    @InjectMocks
    private OwnerUserUseCase ownerUserUseCase;

    private User user;

    public static final String INVALID_USER_DATA ="Invalid user data: ";
    public static final String USER_DATA_NULL = INVALID_USER_DATA+"User data cannot be null";
    public static final String INVALID_USER_ID_TYPE = INVALID_USER_DATA+"User identity type invalid";
    public static final String INVALID_USER_ID_NUMBER = INVALID_USER_DATA+"User identity number cannot be null";
    public static final String USER_NOT_ADULT = INVALID_USER_DATA+"User must be at least 18 years old";
    public static final String INVALID_EMAIL_FORMAT = INVALID_USER_DATA+"Invalid email format";
    public static final String INVALID_PHONE_FORMAT = INVALID_USER_DATA+"Invalid phone number format or length (max 13 digits with optional '+')";


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
    }

    @Test
    void createUser_valid() {
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);

        User createdUser = ownerUserUseCase.createOwnerUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getUserFirstName(), createdUser.getUserFirstName());
        assertEquals(user.getUserSecondName(), createdUser.getUserSecondName());
        assertEquals(user.getUserFirstLastName(), createdUser.getUserFirstLastName());
        assertEquals(user.getUserSecondLastName(), createdUser.getUserSecondLastName());
        assertEquals(user.getUserBirthdate(), createdUser.getUserBirthdate());
        assertEquals(user.getUserPhone(), createdUser.getUserPhone());
        assertEquals(user.getUserEmail(), createdUser.getUserEmail());
        assertEquals(user.getUserPassword(), createdUser.getUserPassword());

    }

    @Test
    void createUser_invalid_user_null(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user = null;

        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(USER_DATA_NULL, exception.getMessage());
    }

    @Test
    void createUser_invalid_email_format_without_at_symbol(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserEmail("pperemail.com");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(INVALID_EMAIL_FORMAT, exception.getMessage());
    }

    @Test
    void createUser_invalid_email_format_without_mail_domain(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserEmail("ppere@mail");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(INVALID_EMAIL_FORMAT, exception.getMessage());
    }

    @Test
    void createUser_invalid_user_age(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserBirthdate(LocalDate.now().minusYears(15));
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(USER_NOT_ADULT, exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_character(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserPhone("#573102334312");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(INVALID_PHONE_FORMAT, exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_lenght(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserPhone("+573102334312222");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(INVALID_PHONE_FORMAT, exception.getMessage());
    }

    @Test
    void createUser_no_identity_type(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserIdentityType(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(INVALID_USER_ID_TYPE, exception.getMessage());
    }

    @Test
    void createUser_no_identity_number(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserIdentityNumber(null);
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> ownerUserUseCase.createOwnerUser(user));
        assertEquals(INVALID_USER_ID_NUMBER, exception.getMessage());
    }


}
