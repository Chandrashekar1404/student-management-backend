package com.IEP_Project.StudentManagementSystem.repository;


import com.IEP_Project.StudentManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByEmail(String email);
    Student findByEmailAndPassword(String email, String password);
}
