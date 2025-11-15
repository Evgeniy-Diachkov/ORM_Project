package com.example.edu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreateDto(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Long categoryId,
        @NotNull Long teacherId
) {}
