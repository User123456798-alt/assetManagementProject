package com.example.assetmanagment.service;

import com.example.assetmanagment.dto.AssetDto;
import com.example.assetmanagment.dto.EmployeeDto;
import com.example.assetmanagment.entity.AssetEntity;
import com.example.assetmanagment.entity.EmployeeEntity;
import com.example.assetmanagment.repository.AssetRepository;
import org.hibernate.collection.spi.PersistentBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssetService {
    private static final Logger logger = LoggerFactory.getLogger(AssetService.class);
    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Page<AssetDto> getAllAssets(Pageable page){
        logger.info("entered find all employees");
        return assetRepository.findAll(page)
                .map(this::toAssetDto);
    }

    public AssetDto getAssetById(Integer id){
        logger.info("entered find employee");
        return assetRepository.findById(id).map(this::toAssetDto).orElse(null);
    }

    public AssetDto makeAsset (AssetDto assetDto) {
        logger.info("entered make asset");
        AssetEntity newAsset = toAssetEntity(assetDto);
        assetRepository.save(newAsset);
        return assetRepository.findById(newAsset.getId()).map(this::toAssetDto).orElse(null);
    }

    public Optional<AssetDto> updateAsset (AssetDto assetDto) throws Exception {
        logger.info("entered update asset");
        AssetEntity oldData = assetRepository.findById(assetDto.id()).orElseThrow(() -> new Exception("something"));
        removeAsset(assetDto.id());
        assetRepository.save(toAssetEntity(assetDto));
        return assetRepository.findById(oldData.getId()).map(this::toAssetDto);
    }

    public Page<String> assetsOfType(Pageable page){
        logger.info("entered assets of type");
        Map<String,Integer> type = new HashMap<>();
        for (AssetEntity e:assetRepository.findAll()){
            if(type.containsKey(e.getAssetType())){
                Integer value = type.get(e.getAssetType());
                type.replace(e.getAssetType(),value+1);
            }
            else{
                type.put(e.getAssetType(),1);
            }
        }
        List<String> finalList = new ArrayList<>();
        for (String s:type.keySet()){
            finalList.add(s+": "+type.get(s));
        }
        return new PageImpl<>(finalList);
    }

    public void removeAsset (Integer id){
        logger.info("entered remove asset");
        assetRepository.delete(assetRepository.getReferenceById(id));
    }

    private AssetDto toAssetDto(AssetEntity asset) {
        logger.info("entered toAssetDto");
        return new AssetDto(
                asset.getId(),
                asset.getAssetName(),
                asset.getAssetType(),
                asset.getSerialNumber(),
                asset.getStatus()
        );
    }

    private AssetEntity toAssetEntity(AssetDto assetDto){
        logger.info("entered toAssetEntity");
        return new AssetEntity(
                assetDto.id(),
                assetDto.assetName(),
                assetDto.assetType(),
                assetDto.serialNumber(),
                assetDto.status()
        );
    }
}
