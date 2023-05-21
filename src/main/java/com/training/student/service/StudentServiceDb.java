package com.training.student.service;

import com.training.student.entity.Student;
import com.training.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.training.student.util.StudentData.STUDENT_LIST;

@Service
public class StudentServiceDb {

    private final StudentRepository studentRepository;

    public StudentServiceDb(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
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
            if (student.getId().equals(id)) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }



    public Optional<Student> updateStudent(Student student) {
        Integer id = student.getId();
        if (id > STUDENT_LIST.size()) {
            return Optional.empty();
        }
        Student retrievedStudent = STUDENT_LIST.get(id - 1);
        retrievedStudent.setAge(student.getAge())
                .setName(student.getName());

        return Optional.of(retrievedStudent);
    }

    public void deleteStudent(Integer id) {
        if (id <= STUDENT_LIST.size()) {
            Student student = STUDENT_LIST.get(id - 1);
            if (student != null) {
                student.delete();
            }
        }
    }
}
