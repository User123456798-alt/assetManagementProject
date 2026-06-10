package com.example.assetmanagment.controller;

import com.example.assetmanagment.dto.EmployeeDto;
import com.example.assetmanagment.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Page<EmployeeDto> getAllEmployees(Pageable page) {
        return employeeService.findAllEmployees(page);
    }

    @GetMapping("/reports/top-employee ")
    public Page<String> mostAssigned(){
        return employeeService.mostAssets();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable Integer id){
        return employeeService.findEmployee(id);
    }

    @PostMapping
    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        return employeeService.makeEmployee(employeeDto);
    }

    @DeleteMapping("/{id}")
    public void removeEmployee(@PathVariable Integer id){
        employeeService.removeEmployee(id);
    }

    @PutMapping
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws Exception {
        return employeeService.updateEmployee(employeeDto).orElse(null);
    }

}
