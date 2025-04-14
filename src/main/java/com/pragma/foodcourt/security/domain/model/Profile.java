package com.pragma.foodcourt.security.domain.model;

import com.pragma.foodcourt.security.domain.enums.ProfileEnum;

import java.util.Set;

public class Profile {

    private ProfileEnum profileId;
    private String profileName;
    private Set<Permission> profilePermissions;

    public ProfileEnum getProfileId() {
        return profileId;
    }

    public void setProfileId(ProfileEnum profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Set<Permission> getProfilePermissions() {
        return profilePermissions;
    }

    public void setProfilePermissions(Set<Permission> profilePermissions) {
        this.profilePermissions = profilePermissions;
    }
}
