package com.example.assetmanagment.dto;

import jakarta.validation.constraints.NotBlank;

public record AssetDto(
        Integer id,
        @NotBlank(message = "asset name should not be empty")
        String assetName,
        @NotBlank(message = "asset type should not be empty")
        String assetType,
        @NotBlank(message = "serial number should not be empty")
        long serialNumber,
        @NotBlank(message = "status should not be empty")
        String status
) {

}
