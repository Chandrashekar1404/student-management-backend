package com.IEP_Project.StudentManagementSystem.service;

import com.IEP_Project.StudentManagementSystem.model.Student;
import com.IEP_Project.StudentManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student addStudent(Student student){
        return repo.save(student);
    }

    public List<Student> getAllStudent(){
        return repo.findAll();
    }

    public boolean deleteStudentByRollno(int rollno) {
        if(repo.existsById(rollno)){
            repo.deleteById(rollno);
            return true;
        }
        return false;
    }

    public boolean updateStudent(int rollno, Student updatedStudent) {
        if(repo.existsById(rollno)){
            updatedStudent.setRollno(rollno);
            repo.save(updatedStudent);
            return true;
        }
        return false;
    }

    public boolean setPassword(String email, String password) {
        Optional<Student> optionalStudent=repo.findByEmail(email);
        if(optionalStudent.isPresent()){
            Student student=optionalStudent.get();
            student.setPassword(password);
            repo.save(student);
            return true;
        }
        return false;
    }

    public boolean validateLogin(String email, String password) {

        Student student=repo.findByEmailAndPassword(email,password);

        return student!=null;
    }

    public Student getStudentByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public Student getStudentByRollno(int rollno) {
        return repo.findById(rollno).orElse(null);
    }
}
