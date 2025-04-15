package com.pragma.foodcourt.security.infrastructure.input.rest;

import com.pragma.foodcourt.security.application.dto.UserRequestDto;
import com.pragma.foodcourt.security.application.handler.IUserHandler;
import com.pragma.foodcourt.security.infrastructure.input.rest.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserResource.ROOT)
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping(value = UserResource.CREATE_USER)
    public ResponseEntity<?> createOwnerUser(@RequestBody UserRequestDto userRequest){
        return new WrapperResponse<>(true,"",userHandler.createUser(userRequest)).createSuccessResponse();
    }

}
