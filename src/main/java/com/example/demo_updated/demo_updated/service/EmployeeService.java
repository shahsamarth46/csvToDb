package com.example.demo_updated.demo_updated.service;

import com.example.demo_updated.demo_updated.customDTO.CreateUserDTO;
import com.example.demo_updated.demo_updated.customDTO.LoginDTO;
import com.example.demo_updated.demo_updated.model.Employee;

public interface EmployeeService {
    String isValidUserAndReturnRole(LoginDTO loginDTO);

    Employee createUser(CreateUserDTO employee, String role);
}
