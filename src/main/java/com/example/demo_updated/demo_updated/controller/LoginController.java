package com.example.demo_updated.demo_updated.controller;

import com.example.demo_updated.demo_updated.customDTO.CreateUserDTO;
import com.example.demo_updated.demo_updated.customDTO.LoginDTO;
import com.example.demo_updated.demo_updated.model.Employee;
import com.example.demo_updated.demo_updated.service.LoginService;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
            String role =  loginService.isValidUserAndReturnRole(loginDTO);
            return role;
    }

    @PostMapping("/create-user")
    public Employee createUser(@RequestParam("role") String role,@RequestBody CreateUserDTO employee){

        Employee e=loginService.createUser(employee,role);
            return e;
    }

    @PostMapping("/updload-csv")
    public void uploadCsv(@RequestParam("file") MultipartFile file){

    }


}
