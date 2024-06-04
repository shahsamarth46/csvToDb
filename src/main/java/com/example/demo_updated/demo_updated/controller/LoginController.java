package com.example.demo_updated.demo_updated.controller;

import com.example.demo_updated.demo_updated.customDTO.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        System.out.println("test....");


    }

}
