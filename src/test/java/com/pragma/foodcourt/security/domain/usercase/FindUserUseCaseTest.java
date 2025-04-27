package com.pragma.foodcourt.security.domain.usercase;

import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.exception.UserNotFoundException;
import com.pragma.foodcourt.security.domain.model.User;
import com.pragma.foodcourt.security.domain.spi.IFindUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FindUserUseCaseTest {

    @Mock
    private IFindUserPersistencePort findUserPersistencePort;

    @InjectMocks
    private FindUserUserCase findUserUserCase;
    private User user;

    private static final int IDENTITY_TYPE = 1;
    private static final String IDENTITY_NUMBER = "5200433";
    private static final int INVALID_IDENTITY_TYPE = 1;
    private static final String INVALID_IDENTITY_NUMBER = "12345";

    public static final String INVALID_USER_DATA ="Invalid user data: ";
    public static final String USER_NOT_FOUND = "User with identity type "+INVALID_IDENTITY_TYPE+" and identity number "+INVALID_IDENTITY_NUMBER+" not found";
    public static final String INVALID_USER_ID_TYPE ="User identity type invalid";
    public static final String INVALID_USER_ID_NUMBER =INVALID_USER_DATA+"User identity number cannot be null";

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
    void findUser_successful(){
        when(findUserPersistencePort.findUserByIdentity(any(Integer.class), any(String.class))).thenReturn(user);
        User findUser = findUserPersistencePort.findUserByIdentity(IDENTITY_TYPE, IDENTITY_NUMBER);

        assertNotNull(findUser);
        assertEquals(user.getUserFirstName(), findUser.getUserFirstName());
        assertEquals(user.getUserSecondName(), findUser.getUserSecondName());
        assertEquals(user.getUserFirstLastName(), findUser.getUserFirstLastName());
        assertEquals(user.getUserSecondLastName(), findUser.getUserSecondLastName());
        assertEquals(user.getUserBirthdate(), findUser.getUserBirthdate());
        assertEquals(user.getUserPhone(), findUser.getUserPhone());
        assertEquals(user.getUserEmail(), findUser.getUserEmail());
        assertEquals(user.getUserPassword(), findUser.getUserPassword());

    }

    @Test
    void findUser_failed(){
        when(findUserPersistencePort.findUserByIdentity(INVALID_IDENTITY_TYPE, INVALID_IDENTITY_NUMBER)).thenReturn(null);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () ->
                findUserUserCase.findUserByIdentity(INVALID_IDENTITY_TYPE, INVALID_IDENTITY_NUMBER));
        assertEquals(USER_NOT_FOUND, exception.getMessage());

    }

    @Test
    void findUser_invalid_identity_type(){
        when(findUserPersistencePort.findUserByIdentity(0, IDENTITY_NUMBER)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                findUserUserCase.findUserByIdentity(0, IDENTITY_NUMBER));

        assertEquals(INVALID_USER_ID_TYPE,exception.getMessage());
    }

    @Test
    void findUser_invalid_identity_number_null(){
        when(findUserPersistencePort.findUserByIdentity(IDENTITY_TYPE, null)).thenReturn(null);

        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                findUserUserCase.findUserByIdentity(IDENTITY_TYPE,null));

        assertEquals(INVALID_USER_ID_NUMBER, exception.getMessage());
    }

}
