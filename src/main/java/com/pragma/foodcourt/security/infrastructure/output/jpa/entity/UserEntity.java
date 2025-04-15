package com.pragma.foodcourt.security.infrastructure.output.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "t_user")
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(name = "user_id",unique = true, columnDefinition = "UUID DEFAULT gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(name = "user_identity_type", nullable = false)
    private int userIdentityType;
    @Column(name = "user_identity_number", length = 50, nullable = false)
    private String userIdentityNumber;
    @Column(name = "user_first_name", length = 30, nullable = false)
    private String userFirstName;
    @Column(name = "user_second_name", length = 30)
    private String userSecondName;
    @Column(name = "user_first_lastname", length = 30, nullable = false)
    private String userFirstLastName;
    @Column(name = "user_second_lastname", length = 30)
    private String userSecondLastName;
    @Column(name = "user_phone", length = 13, nullable = false)
    private String userPhone;
    @Column(name = "user_birthdate", nullable = false)
    private LocalDate userBirthdate;
    @Column(name = "user_email",length = 50, nullable = false)
    private String userEmail;
    @Column(name = "user_password",length = 500, nullable = false)
    private String userPassword;
    @Column(name = "user_active", nullable = false)
    private boolean userActive;

    @ManyToMany
    @JoinTable(name = "t_user_profile",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<ProfileEntity> userProfiles;

}
