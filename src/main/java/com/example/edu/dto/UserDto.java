package com.example.edu.dto;

import com.example.edu.domain.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotBlank String name,
        @Email String email,
        @NotNull UserRole role
) {}
