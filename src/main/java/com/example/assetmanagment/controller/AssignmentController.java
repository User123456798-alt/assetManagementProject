package com.example.assetmanagment.controller;

import com.example.assetmanagment.dto.AssignmentDto;
import com.example.assetmanagment.service.AssetService;
import com.example.assetmanagment.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public AssignmentDto assign(AssignmentDto assignmentDto) throws Exception {
        return assignmentService.assignAsset(assignmentDto);
    }

    @PutMapping("/{id}/return")
    public AssignmentDto returnAsset(@PathVariable Integer id) throws Exception {
        return assignmentService.returnAsset(id);
    }
}
