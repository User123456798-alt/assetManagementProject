package com.example.assetmanagment.service;

import com.example.assetmanagment.dto.EmployeeDto;
import com.example.assetmanagment.entity.EmployeeEntity;
import com.example.assetmanagment.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    @Autowired
    AssignmentService asi;

    @Autowired
    AssetService asp;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


        public Page<EmployeeDto> findAllEmployees (Pageable page) {
                logger.info("entered find all employees");
                return employeeRepository.findAll(page)
                        .map(this::toEmployeeDto);
        }

        public Page<String> mostAssets(){
            logger.info("entered most assets");
            EmployeeEntity maxEmployee = new EmployeeEntity(null,null,null,null,null);
            int max = 0;
            Map<String,Integer> amount = new HashMap<>();
            for (EmployeeEntity e:employeeRepository.findAll()){
                amount.put(e.getFirstName(),asi.allAssetsAssigned(e.getEmployeeId()).size());
            }
            List<String> finalList = new ArrayList<>();
            for (String s:amount.keySet()){
                finalList.add(s+": "+amount.get(s));
            }
            return new PageImpl<>(finalList);
        }


        public EmployeeDto findEmployee(Integer id){
            logger.info("entered find employee");
            return employeeRepository.findById(id).map(this::toEmployeeDto).orElse(null);
        }

        public void removeEmployee (Integer id){
            logger.info("entered remove employee");
            employeeRepository.delete(employeeRepository.getReferenceById(id));
        }

        public Optional<EmployeeDto> updateEmployee (EmployeeDto employeeDto) throws Exception {
            logger.info("entered update employee");
            EmployeeEntity oldData = employeeRepository.findById(employeeDto.employeeId()).orElseThrow(() -> new Exception("something"));
            removeEmployee(employeeDto.employeeId());
            employeeRepository.save(toEntity(employeeDto));
            return employeeRepository.findById(oldData.getEmployeeId()).map(this::toEmployeeDto);
        }

        public EmployeeDto makeEmployee (EmployeeDto employeeDto) {
            logger.info("entered make employee");
            EmployeeEntity newEmployee = toEntity(employeeDto);
            employeeRepository.save(newEmployee);
            return employeeRepository.findById(newEmployee.getEmployeeId()).map(this::toEmployeeDto).orElse(null);
        }

        private EmployeeDto toEmployeeDto(EmployeeEntity entity) {
            logger.info("entered toEmployeeDto");
            return new EmployeeDto(
                    entity.getEmployeeId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getDepartment(),
                    entity.getEmail()
            );
        }

        private EmployeeEntity toEntity(EmployeeDto dto){
            logger.info("entered to employeeEntity");
            return new EmployeeEntity(
                    dto.employeeId(),
                    dto.userFirstName(),
                    dto.userLastName(),
                    dto.department(),
                    dto.email());
    }
}
