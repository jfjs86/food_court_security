package com.pragma.foodcourt.security.infrastructure.output.jpa.repository;

import com.pragma.foodcourt.security.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUserIdentityTypeAndUserIdentityNumber(@Param("userIdentityType") int userIdentityType, @Param("userIdentityNumber") String userIdentityNumber);



}
