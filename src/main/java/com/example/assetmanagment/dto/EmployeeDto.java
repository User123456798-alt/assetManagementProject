package com.example.assetmanagment.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * API representation of a row in the {@code user} table.
 */
public record EmployeeDto(
        Integer employeeId,
        @NotBlank(message = "first name should not be empty")
        String userFirstName,
        @NotBlank(message = "last name should not be empty")
        String userLastName,
        @NotBlank(message = "department should not be empty")
        String department,
        @NotBlank(message = "email should not be empty")
        String email
) {
}
