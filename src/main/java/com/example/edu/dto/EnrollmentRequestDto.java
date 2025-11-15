package com.example.edu.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDto(
        @NotNull Long studentId,
        @NotNull Long courseId
) {}
