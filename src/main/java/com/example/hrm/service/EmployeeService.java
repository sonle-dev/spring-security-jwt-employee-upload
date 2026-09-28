package com.example.hrm.service;

import com.example.hrm.dto.EmployeeCreateDTO;
import com.example.hrm.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(EmployeeCreateDTO dto);
    List<Employee> getAllEmployees();
}