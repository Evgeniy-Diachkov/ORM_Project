package com.example.edu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssignmentCreateDto(
        @NotNull Long lessonId,
        @NotBlank String title,
        @NotBlank String description
) {}
