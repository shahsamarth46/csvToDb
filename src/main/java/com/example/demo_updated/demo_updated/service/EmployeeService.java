package com.example.demo_updated.demo_updated.service;

import com.example.demo_updated.demo_updated.customDTO.CreateUserDTO;
import com.example.demo_updated.demo_updated.customDTO.LoginDTO;
import com.example.demo_updated.demo_updated.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface EmployeeService {
    String isValidUserAndReturnRole(LoginDTO loginDTO);

    Employee createUser(CreateUserDTO employee, String role);

    Map<String,Integer> uploadCsv(MultipartFile file) throws IOException;
}
