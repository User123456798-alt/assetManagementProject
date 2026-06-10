package com.example.assetmanagment.dto;

import jakarta.validation.constraints.NotBlank;

import javax.management.remote.JMXServerErrorException;
import java.math.BigInteger;
import java.util.Date;

public record AssignmentDto(
        int id,

        @NotBlank(message = "employee id should not be empty")
        BigInteger employeeId,

        @NotBlank(message = "asset id should not be empty")
        BigInteger assetId,

        @NotBlank(message = "assigned data should not be empty")
        Date assignedDate,

        Date returnedDate
) {
}
