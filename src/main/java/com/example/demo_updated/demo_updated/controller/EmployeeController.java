package com.example.demo_updated.demo_updated.controller;

import com.example.demo_updated.demo_updated.customDTO.CreateUserDTO;
import com.example.demo_updated.demo_updated.customDTO.LoginDTO;
import com.example.demo_updated.demo_updated.model.Employee;
import com.example.demo_updated.demo_updated.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
            String role =  employeeService.isValidUserAndReturnRole(loginDTO);
            return role;
    }

    @PostMapping("/create-user")
    public Employee createUser(@RequestParam("role") String role,@RequestBody CreateUserDTO employee){

        Employee e= employeeService.createUser(employee,role);
            return e;
    }

    @PostMapping("/updload-csv")
    public Map<String,Integer> uploadCsv(@RequestParam("file") MultipartFile file) throws Exception {

           Map<String,Integer> hmap = employeeService.uploadCsv(file);
           return hmap;
    }


}
