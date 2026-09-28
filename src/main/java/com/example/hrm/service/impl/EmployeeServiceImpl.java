package com.example.hrm.service.impl;

import com.example.hrm.dto.EmployeeCreateDTO;
import com.example.hrm.entity.Employee;
import com.example.hrm.repository.EmployeeRepository;
import com.example.hrm.service.CloudinaryService;
import com.example.hrm.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CloudinaryService cloudinaryService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CloudinaryService cloudinaryService) {
        this.employeeRepository = employeeRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Employee createEmployee(EmployeeCreateDTO dto) {
        String avatarUrl = cloudinaryService.uploadFile(dto.getAvatarFile());

        Employee employee = Employee.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .avatarUrl(avatarUrl)
                .salary(0.0)
                .build();

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}