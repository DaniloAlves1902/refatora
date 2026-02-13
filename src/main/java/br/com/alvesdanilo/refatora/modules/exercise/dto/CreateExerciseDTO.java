package br.com.alvesdanilo.refatora.modules.exercise.dto;

import br.com.alvesdanilo.refatora.modules.user.model.SeniorityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateExerciseDTO(
        @NotBlank(message = "Title is required")
        @Size(min = 5, max = 150)
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Slug is required")
        String slug,

        @NotNull(message = "Difficulty is required")
        SeniorityLevel difficulty,

        @NotBlank(message = "Technology is required")
        String technology

) {}