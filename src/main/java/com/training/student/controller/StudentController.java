package com.training.student.controller;

import com.training.student.entity.Student;
import com.training.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //dependency injection
    //1. Annotation @Autowired
    //    @Autowired
    //    private StudentService service;

    //2. Constructor Injection
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    //3. Field Injection


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        if (id < 1) {
            return ResponseEntity.badRequest().build();
        }

        Student student = service.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok().body(student);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<Student> getStudentOptional(@PathVariable Integer id) {
        if (id < 1) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Student> studentOptional = service.getStudentOptional(id);

//        if (studentOptional.isPresent()) {
//            return ResponseEntity.ok().body(studentOptional.get());
//        }else {
//            return ResponseEntity.notFound().build();
//        }

        return studentOptional
                .map(student -> ResponseEntity.ok().body(student))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
