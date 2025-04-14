package com.pragma.foodcourt.security.domain.model;

import com.pragma.foodcourt.security.domain.enums.IdentityTypeEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class User {

    private UUID userId;
    private IdentityTypeEnum userIdentityType;
    private String userIdentityNumber;
    private String userFirstName;
    private String userSecondName;
    private String userFirstLastName;
    private String userSecondLastName;
    private String phone;
    private LocalDate userBirthdate;
    private String userEmail;
    private String userPassword;
    private boolean userActive;
    private Set<Profile> userProfiles;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public IdentityTypeEnum getUserIdentityType() {
        return userIdentityType;
    }

    public void setUserIdentityType(IdentityTypeEnum userIdentityType) {
        this.userIdentityType = userIdentityType;
    }

    public String getUserIdentityNumber() {
        return userIdentityNumber;
    }

    public void setUserIdentityNumber(String userIdentityNumber) {
        this.userIdentityNumber = userIdentityNumber;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public String getUserFirstLastName() {
        return userFirstLastName;
    }

    public void setUserFirstLastName(String userFirstLastName) {
        this.userFirstLastName = userFirstLastName;
    }

    public String getUserSecondLastName() {
        return userSecondLastName;
    }

    public void setUserSecondLastName(String userSecondLastName) {
        this.userSecondLastName = userSecondLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(LocalDate userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserActive() {
        return userActive;
    }

    public void setUserActive(boolean userActive) {
        this.userActive = userActive;
    }

    public Set<Profile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<Profile> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
