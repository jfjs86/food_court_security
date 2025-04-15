package com.pragma.foodcourt.security.application.dto;

import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;
import com.pragma.foodcourt.security.domain.model.Profile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserRequestDto {

    private int userIdentityType;
    private String userIdentityNumber;
    private String userFirstName;
    private String userSecondName;
    private String userFirstLastName;
    private String userSecondLastName;
    private String userPhone;
    private LocalDate userBirthdate;
    private String userEmail;
    private String userPassword;


}
