package com.IEP_Project.StudentManagementSystem.service;

import com.IEP_Project.StudentManagementSystem.model.Admin;
import com.IEP_Project.StudentManagementSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repo;

    public boolean validateLogin(String email, String password) {
//        System.out.println("Checking login for email: " + email + " | password: " + password);
        Admin admin = repo.findByEmailAndPassword(email, password);

//        if (admin != null) {
//            System.out.println("Admin found in DB!");
//        } else {
//            System.out.println("Admin not found. Invalid credentials.");
//        }

        return admin != null;
    }
}

