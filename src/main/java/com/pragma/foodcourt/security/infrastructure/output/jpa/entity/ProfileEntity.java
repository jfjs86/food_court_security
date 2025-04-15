package com.pragma.foodcourt.security.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "t_profile")
@Getter
@Setter
public class ProfileEntity {

    @Id
    @Column(name = "profile_id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;

    @Column(name = "profile_name", length = 100, nullable = false)
    private String profileName;

    @Column(name = "profile_status", nullable = false)
    private boolean profileStatus;

    @ManyToMany(mappedBy = "userProfiles")
    private Set<UserEntity> userProfiles;

    @ManyToMany
    @JoinTable(name = "t_profile_permission",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissions;
}
