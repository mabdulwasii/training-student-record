package com.training.student.controller;

import com.training.student.model.Student;
import com.training.student.util.StudentData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(StudentData.populate());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        if(id < 1) {
            return ResponseEntity.badRequest().build();
        }
        for (Student student : StudentData.populate()) {
            if (student.getId() == id) {
                return ResponseEntity.ok().body(student);
            }
        }
       return ResponseEntity.notFound().build();
    }
}
