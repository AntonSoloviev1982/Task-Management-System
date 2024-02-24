package com.solovyev_anton.taskmanagementsystem.controllers;

import com.solovyev_anton.taskmanagementsystem.dtos.JwtRequest;
import com.solovyev_anton.taskmanagementsystem.dtos.JwtResponse;
import com.solovyev_anton.taskmanagementsystem.dtos.RegisterDto;
import com.solovyev_anton.taskmanagementsystem.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Создание нового jwt токена",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Созданный jwt токен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = JwtResponse.class)
                            )
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Username и password",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = JwtRequest.class)
                    )
            )
    )
    @PostMapping("auth")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest authRequest) {
        JwtResponse jwtResponse = authService.createAuthToken(authRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @Operation(summary = "Регистрация нового пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новый пользователь",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegisterDto.class)
                    )
            )
    )
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
