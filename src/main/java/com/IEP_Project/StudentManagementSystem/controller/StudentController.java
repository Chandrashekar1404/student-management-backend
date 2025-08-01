package com.IEP_Project.StudentManagementSystem.controller;


import com.IEP_Project.StudentManagementSystem.model.Student;
import com.IEP_Project.StudentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        try{
            Student student1=service.addStudent(student);
            return ResponseEntity.status(201).body(student1);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body("Failed to add student : "+ e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students=service.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/delete/{rollno}")
    public ResponseEntity<String> deleteStudent(@PathVariable int rollno){
        boolean deleted=service.deleteStudentByRollno(rollno);
        if(deleted){
            return ResponseEntity.ok("Student deleted Successfully");
        }else {
            return ResponseEntity.status(404).body("student not found");
        }
    }

    @PutMapping("/update/{rollno}")
    public ResponseEntity<String> updateStudent(@PathVariable int rollno,@RequestBody Student updatedStudent){
        boolean updated=service.updateStudent(rollno,updatedStudent);

        if(updated){
            return ResponseEntity.ok("Student Updated Successfully");
        }else {
            return ResponseEntity.status(404).body("Student not Found");
        }
    }



    @PutMapping("/set-password")
    public ResponseEntity<String> setStudentPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        boolean updated = service.setPassword(email, password);
        if (updated) {
            return ResponseEntity.ok("Password set successfully.");
        } else {
            return ResponseEntity.status(404).body("Student with given email not found.");
        }
    }

    @PostMapping("/student-login")
    public ResponseEntity<?> studentLogin(@RequestBody Map<String,String> loginData){
        String email=loginData.get("email");
        String password=loginData.get("password");

        Student student = service.getStudentByEmailAndPassword(email, password);
        if (student != null) {
            return ResponseEntity.ok(student);  // Return student JSON
        } else {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }

    @GetMapping("/{rollno}")
    public ResponseEntity<?> getStudentByRollno(@PathVariable int rollno) {
        Student student = service.getStudentByRollno(rollno);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(404).body("Student not found");
        }
    }
}
