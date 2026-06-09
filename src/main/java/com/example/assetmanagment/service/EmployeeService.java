package com.example.assetmanagment.service;

import com.example.assetmanagment.dto.EmployeeDto;
import com.example.assetmanagment.entity.EmployeeEntity;
import com.example.assetmanagment.repository.EmployeeRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


        public Page<EmployeeDto> findAllEmployees (Pageable page) {
                logger.info("entered find all employees");
                return employeeRepository.findAll(page)
                        .map(this::toDto);
        }


        public EmployeeDto findEmpolyee (Integer id){
        logger.info("entered find employee");
        return employeeRepository.findById(id).map(this::toDto).orElse(null);
        }

        public void removeEmployee (Integer id){
        try {
            logger.info("entered remove employee");
            employeeRepository.delete(employeeRepository.getReferenceById(id));
        }
        catch (Exception e){
            logger.error("e: ", e);
        }
        }

        public EmployeeDto updateEmployee (EmployeeDto employeeDto) throws Exception {
        logger.info("entered update employee");
        EmployeeEntity oldData = employeeRepository.findById(employeeDto.employeeId()).orElseThrow(() -> new Exception("something"));
        employeeRepository.save(employeeDto);
        return employeeRepository.findById(oldData.getEmployeeId()).map(this::toDto);
        }

        public EmployeeDto makeEmployee () {
        EmployeeEntity newEmployee = new EmployeeEntity();
        employeeRepository.save(newEmployee);
        return employeeRepository.findById(newEmployee.getEmployeeId()).map(this::toDto);
    }

        private EmployeeDto toDto (EmployeeEntity entity) {
            return new EmployeeDto(
                    entity.getEmployeeId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getDepartment(),
                    entity.getEmail()
            );
        }

        private EmployeeEntity toEntity(EmployeeDto dto){
            return new EmployeeEntity(
                    dto.employeeId(),
                    dto.userFirstName(),
                    dto.userLastName(),
                    dto.department(),
                    dto.email());
        }
    }

}
