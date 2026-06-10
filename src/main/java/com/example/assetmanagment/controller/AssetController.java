package com.example.assetmanagment.controller;

import com.example.assetmanagment.dto.AssetDto;
import com.example.assetmanagment.service.AssetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public Page<AssetDto> getAllAsset(Pageable page) {
        return assetService.getAllAssets(page);
    }

    @GetMapping("/{id}")
    public AssetDto getAsset(@PathVariable Integer id){
        return assetService.getAssetById(id);
    }

    @GetMapping("/reports/assets-by-type")
    public Page<String> getAssetsOfType(Pageable page){
        return assetService.assetsOfType(page);
    }

    @PostMapping
    public AssetDto addAsset(AssetDto assetDto){
        return assetService.makeAsset(assetDto);
    }

    @DeleteMapping("/{id}")
    public void removeAsset(@PathVariable Integer id){
        assetService.removeAsset(id);
    }

    @PutMapping
    public AssetDto updateAsset(AssetDto assetDto) throws Exception {
        return assetService.updateAsset(assetDto).orElse(null);
    }


}
