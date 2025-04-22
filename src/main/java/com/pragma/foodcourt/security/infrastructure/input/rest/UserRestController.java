package com.pragma.foodcourt.security.infrastructure.input.rest;

import com.pragma.foodcourt.security.application.dto.UserRequestDto;
import com.pragma.foodcourt.security.application.dto.UserResponseDto;
import com.pragma.foodcourt.security.application.handler.IUserHandler;
import com.pragma.foodcourt.security.infrastructure.input.rest.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserResource.ROOT)
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping(value = UserResource.CREATE_USER)
    @Operation(summary = "User creation",
            description = "User creation for Food Court Application.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error (unknow exception)"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "409", description = "User Already Exists")
            })
    public ResponseEntity<WrapperResponse<UserResponseDto>> createOwnerUser(@RequestBody UserRequestDto userRequest){
        return new WrapperResponse<UserResponseDto>(true,"",userHandler.createUser(userRequest)).createSuccessResponse();
    }

    @GetMapping(value = UserResource.GET_USER_BY_TYPE_AND_NUMBER_IDENTITY)
    @Operation(summary = "Get user by identity",
            description = "Get user by identity type and number identity.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get exist User",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error (unknow exception)"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<WrapperResponse<UserResponseDto>> getUserByTypeAndNumberIdentity(@RequestParam int identityType, @RequestParam String identityNumber){
        return new WrapperResponse<UserResponseDto>(true,
                "",
                userHandler.getUserByTypeAndNumberIdentity(identityType,identityNumber))
                .createSuccessResponse();
    }

}
