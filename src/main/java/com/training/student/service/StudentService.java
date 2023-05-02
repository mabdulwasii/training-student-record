package com.training.student.service;

import com.training.student.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentService {

    public List<Student> getAllStudents() {
        return null;
    }

    public Student getStudent(@PathVariable Integer id) {
        return null;
    }
}
