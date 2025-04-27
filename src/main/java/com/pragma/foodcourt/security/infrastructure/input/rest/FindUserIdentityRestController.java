package com.pragma.foodcourt.security.infrastructure.input.rest;

import com.pragma.foodcourt.security.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.security.application.handler.IFindUserHandler;
import com.pragma.foodcourt.security.infrastructure.input.rest.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserResource.ROOT)
@RequiredArgsConstructor
public class FindUserIdentityRestController {

    private final IFindUserHandler findUserHandler;

    @GetMapping(value = UserResource.GET_USER_BY_IDENTITY)
    @Operation(summary = "Find user by identity",
            description = "Find user by identity for Food Court Application.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error (unknow exception)")
            })
    public ResponseEntity<WrapperResponse<UserResponseDto>> findUserByIdentity(
            @RequestParam int userIdentityType,
            @RequestParam String userIdentityNumber){
        return new WrapperResponse<UserResponseDto>(
                true,
                "",
                findUserHandler.findUserByIdentity(userIdentityType, userIdentityNumber))
                .createSuccessResponse();
    }
}
