package com.example.assetmanagment.repository;

import com.example.assetmanagment.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Integer> {
}
