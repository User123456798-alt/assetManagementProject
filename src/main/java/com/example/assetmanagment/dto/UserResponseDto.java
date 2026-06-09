package com.example.assetmanagment.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * API representation of a row in the {@code user} table.
 */
public record UserResponseDto(
        String userId,
        @NotBlank(message = "first name should not be empty")
        String userFirstName,
        @NotBlank(message = "last name should not be empty")
        String userLastName
) {
}
