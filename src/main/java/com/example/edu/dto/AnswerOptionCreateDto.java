package com.example.edu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record AnswerOptionCreateDto(
        @NotNull Long questionId,
        @NotBlank String text,
        @NotNull Boolean correct
) {}
