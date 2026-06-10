package com.example.assetmanagment.service;

import com.example.assetmanagment.dto.AssetDto;
import com.example.assetmanagment.dto.AssignmentDto;
import com.example.assetmanagment.entity.AssignmentEntity;
import com.example.assetmanagment.repository.AssignmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService {

    @Autowired
    EmployeeService emp;

    @Autowired
    AssetService ast;

    private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public AssignmentDto assignAsset(AssignmentDto assignment) throws Exception {
        logger.info("entered assign asset");
        AssignmentEntity newAssignment = toAssignmentEntity(assignment);
        assignmentRepository.save(newAssignment);
        Integer id = assignment.assetId().intValue();
        if (!ast.getAssetById(id).status().equals("AVAILABLE")){
            System.out.println("invalid assignment");
            return null;
        }
        ast.updateAsset(new AssetDto (ast.getAssetById(id).id(),
                ast.getAssetById(id).assetName(),
                ast.getAssetById(id).assetType(),
                ast.getAssetById(id).serialNumber(),
                "ASSIGNED"));
        return assignmentRepository.findById(newAssignment.getId()).map(this::toAssignmentDto).orElse(null);
    }

    public ArrayList<AssignmentEntity> allAssetsAssigned(Integer id){
        ArrayList<AssignmentEntity> assigned = new ArrayList<>();
        for(AssignmentEntity a :assignmentRepository.findAll()){
            if (a.getEmployeeId().intValue() == id) {
                assigned.add(a);
            }
        }
        return assigned;
    }


    public AssignmentDto returnAsset(Integer id) throws Exception {
        logger.info("entered return assignment");
        AssignmentEntity returned = assignmentRepository.findById(id).orElseThrow();
        ast.updateAsset(new AssetDto (ast.getAssetById(id).id(),
                ast.getAssetById(id).assetName(),
                ast.getAssetById(id).assetType(),
                ast.getAssetById(id).serialNumber(),
                "AVAILABLE"));
        return assignmentRepository.findById(id).map(this::toAssignmentDto).orElseThrow();
    }

    public void removeAssignment (Integer id){
        logger.info("entered remove assignment");
        assignmentRepository.delete(assignmentRepository.getReferenceById(id));
    }

    private AssignmentDto toAssignmentDto(AssignmentEntity entity){
        logger.info("entered to assignmentDto");
        if (ast.getAssetById(entity.getAssetId().intValueExact())==null){
            logger.info("no asset with given id");
            throw new NullPointerException();
        }
        else if(emp.findEmployee(entity.getEmployeeId().intValueExact())==null){
            logger.info("no employee with given id");
            throw new NullPointerException();
        }
        return new AssignmentDto(
                entity.getId(),
                entity.getEmployeeId(),
                entity.getAssetId(),
                entity.getAssignedDate(),
                entity.getReturnedDate()
        );
    }

    private AssignmentEntity toAssignmentEntity(AssignmentDto dto){
        return new AssignmentEntity(
                dto.id(),
                dto.employeeId(),
                dto.assetId(),
                dto.assignedDate(),
                dto.returnedDate()
        );
    }
}
