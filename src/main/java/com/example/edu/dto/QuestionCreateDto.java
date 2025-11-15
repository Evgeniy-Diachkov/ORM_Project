package com.example.edu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionCreateDto(
        @NotNull Long quizId,
        @NotBlank String text,
        @NotBlank String type
) {}
