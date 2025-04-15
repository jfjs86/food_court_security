package com.pragma.foodcourt.security.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "t_permission")
@Getter
@Setter
public class PermissionEntity {

    @Id
    @Column(name = "permission_id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionId;

    @Column(name = "permission_name", length = 100, nullable = false)
    private String permissionName;

    @Column(name = "permission_status", nullable = false)
    private boolean functionStatus;

    @ManyToMany(mappedBy = "permissions")
    private Set<ProfileEntity> profiles;
}
