package com.pragma.foodcourt.security.infrastructure.input.rest;

import com.pragma.foodcourt.security.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.security.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.security.application.handler.IOwnerUserHandler;
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
public class OwnerUserRestController {

    private final IOwnerUserHandler ownerUserHandler;

    @PostMapping(value = UserResource.CREATE_OWNER_USER)
    @Operation(summary = "User owner creation",
            description = "User owner creation for Food Court Application.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error (unknow exception)"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "409", description = "User Already Exists")
            })
    public ResponseEntity<WrapperResponse<UserResponseDto>> createOwnerUser(@RequestBody UserRequestDto userRequest){
        return new WrapperResponse<UserResponseDto>(true,"",ownerUserHandler.createOwnerUser(userRequest)).createSuccessResponse();
    }

}
