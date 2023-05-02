package com.training.student.service;

import com.training.student.entity.Student;
import com.training.student.util.StudentData;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    public List<Student> getAllStudents() {
        return StudentData.populate();
    }

    public Student getStudent(@PathVariable Integer id) {
        for (Student student : getAllStudents()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return  null;
    }

    public Optional<Student> getStudentOptional(@PathVariable Integer id) {
        for (Student student : getAllStudents()) {
            if (student.getId() == id) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }
}
