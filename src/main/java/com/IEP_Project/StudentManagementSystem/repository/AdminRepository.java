package com.IEP_Project.StudentManagementSystem.repository;

import com.IEP_Project.StudentManagementSystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
    Admin findByEmailAndPassword(String email,String password);
}
