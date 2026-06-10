package com.example.assetmanagment.repository;

import com.example.assetmanagment.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<AssetEntity, Integer> {
}
