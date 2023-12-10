package com.solovyev_anton.taskmanagementsystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Role role;

}
