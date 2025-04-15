package com.pragma.foodcourt.security.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
