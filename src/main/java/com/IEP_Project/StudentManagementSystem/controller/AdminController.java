package com.IEP_Project.StudentManagementSystem.controller;


import com.IEP_Project.StudentManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {

    @Autowired
    private AdminService service;

//    @GetMapping("/")
//    public String greet(){
//        return "hello admin";
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> loginData){
        String email=loginData.get("email");
        String password=loginData.get("password");

        if(service.validateLogin(email,password)){
            return ResponseEntity.ok("Login Successful");
        }else{
            return ResponseEntity.status(401).body("Invalid Credentials");
        }

    }

}
