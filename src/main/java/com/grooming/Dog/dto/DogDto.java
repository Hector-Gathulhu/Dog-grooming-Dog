package com.grooming.Dog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DogDto(
        @NotBlank
        String name,
        @NotBlank
        String age,
        @NotNull
        Long ownerId
) {
}
